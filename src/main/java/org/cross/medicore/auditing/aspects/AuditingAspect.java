package org.cross.medicore.auditing.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cross.medicore.auditing.annotations.Auditable;
import org.cross.medicore.auditing.service.AuditingService;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditingAspect {
    private final AuditingService auditingService;

    @AfterReturning("@annotation(auditable)")
    public void log(JoinPoint joinPoint, Auditable auditable) throws Throwable {
        auditingService.registerLog(
                auditable.action(),
                auditable.message()
        );
    }

}
