package com.example.HL_LAND.module.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.system.entity.SysUser;

@Entity
@Table(name = "app_notification_recipients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationRecipient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id", nullable = false)
    private Notification notification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser recipient; // Tài khoản người nhận thông báo

    @Column(name = "is_read")
    @Builder.Default
    private Boolean isRead = false;

    @Column(name = "read_at")
    private LocalDateTime readAt; // Thời điểm đọc thông báo
}
