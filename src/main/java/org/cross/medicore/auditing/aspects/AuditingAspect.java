package org.cross.medicore.auditing.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.cross.medicore.auditing.annotation.Auditable;
import org.cross.medicore.auditing.service.AuditingService;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditingAspect {
    private final AuditingService auditingService;

    @AfterReturning("@annotation(auditable)")
    public void log(JoinPoint joinPoint, Auditable auditable) throws Throwable {
        Object [] args = joinPoint.getArgs();

        auditingService.registerLog(
                auditable.action(),
                normalizeMessage(auditable.message(), args)
        );
    }

    private String normalizeMessage(String parameterizedMessage, Object[] args) {
        StringBuilder normalizedMessage = new StringBuilder(parameterizedMessage);

        int paramIdx = 0;
        while ((paramIdx = normalizedMessage.indexOf("?", paramIdx)) != -1) {
            if (paramIdx + 1 >= normalizedMessage.length()) {
                break;
            }

            char nextChar = normalizedMessage.charAt(paramIdx + 1);
            if (!Character.isDigit(nextChar)) {
                paramIdx++;
                continue;
            }

            int argNum = Character.getNumericValue(nextChar);

            if (argNum <= 0 || argNum > args.length) {
                paramIdx += 2;
                continue;
            }

            String arg = args[argNum - 1] != null ? args[argNum - 1].toString() : "null";
            normalizedMessage.replace(paramIdx, paramIdx + 2, arg);
            paramIdx += arg.length();
        }

        return normalizedMessage.toString();
    }


}
