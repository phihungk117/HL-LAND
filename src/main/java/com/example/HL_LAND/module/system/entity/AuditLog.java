package com.example.HL_LAND.module.system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_audit_logs", indexes = {
    @Index(name = "idx_audit_logs_entity", columnList = "entity_type, entity_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "entity_type", length = 100)
    private String entityType; // Loại thực thể bị tác động hệ thống
    
    @Column(name = "entity_id")
    private Long entityId; // ID thực thể bị tác động
    
    @Column(name = "action", length = 50)
    private String action; // Hành động thực hiện (CREATE/UPDATE/DELETE/VIEW)
    
    @Column(name = "actor", length = 100)
    private String actor; // Người thực hiện hành động
    
    @Column(name = "old_value", columnDefinition = "TEXT")
    private String oldValue; // Giá trị cũ trước khi thay đổi
    
    @Column(name = "new_value", columnDefinition = "TEXT")
    private String newValue; // Giá trị mới sau khi thay đổi
    
    @Column(name = "ip_address", length = 50)
    private String ipAddress; // Địa chỉ IP người dùng tác động
    
    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason; // Diễn giải lý do thu/chi

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
