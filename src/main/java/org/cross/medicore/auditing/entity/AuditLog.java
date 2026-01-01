package org.cross.medicore.auditing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.cross.medicore.shared.Action;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Entity
@Table(name = "audit_logs")
@Getter
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", nullable = false, updatable = false)
    private long userId;

    @Column(name = "user_ip", nullable = false, updatable = false)
    private String userIp;

    @Enumerated(EnumType.STRING)
    @Column(name = "action", nullable = false, updatable = false)
    private Action action;

    @Column(name = "description", nullable = false, updatable = false)
    private String description;


    @Column(name = "timestamp", nullable = false, updatable = false)
    private OffsetDateTime timestamp;


    public AuditLog() {
    }

    private AuditLog(long userId, String userIp, Action action, String description, OffsetDateTime timestamp) {
        this.userId = userId;
        this.userIp = userIp;
        this.action = action;
        this.description = description;
        this.timestamp = timestamp;
    }

    public static AuditLogBuilder builder(){
        return new AuditLogBuilder();
    }

    public static class AuditLogBuilder{
        private long userId;
        private String userIp;
        private Action action;
        private String description;
        private OffsetDateTime timestamp;

        public AuditLogBuilder userId(long userId){
            this.userId = userId;
            return this;
        }

        public AuditLogBuilder userIp(String userIp){
            this.userIp = userIp;
            return this;
        }

        public AuditLogBuilder action(Action action){
            this.action = action;
            return this;
        }

        public AuditLogBuilder description(String desc){
            this.description = desc;
            return this;
        }

        public AuditLogBuilder timestamp(OffsetDateTime timestamp){
            this.timestamp = timestamp;
            return this;
        }

        public AuditLog build(){
            return new AuditLog(
                    this.userId,
                    this.userIp,
                    this.action,
                    this.description,
                    this.timestamp
            );
        }
    }
}
