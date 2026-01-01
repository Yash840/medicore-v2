package org.cross.medicore.auditing.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.auditing.entity.AuditLog;
import org.cross.medicore.auditing.persistence.AuditLogRepository;
import org.cross.medicore.security.api.UserSecurityApi;
import org.cross.medicore.shared.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class AuditingService {
    private final AuditLogRepository auditLogRepository;
    private final UserSecurityApi userApi;

    @Autowired
    @Lazy
    private HttpServletRequest request;

    private String getAuthenticatedUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if(principal instanceof String s)
            return s;

        throw new RuntimeException("authenticated user not found");
    }

    private long getAuthenticatedUserId(){
        String username = getAuthenticatedUsername();

        return userApi.getUser(username).getId();
    }

    private String getUserIp() {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    @Async
    public void registerLog(Action action, String description){
        AuditLog auditLog = AuditLog.builder()
                .userId(getAuthenticatedUserId())
                .userIp(getUserIp())
                .action(action)
                .description(description)
                .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                .build();

        auditLogRepository.save(auditLog);
    }
}
