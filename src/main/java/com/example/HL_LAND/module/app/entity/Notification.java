package com.example.HL_LAND.module.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "title", nullable = false, length = 500)
    private String title; // Tiêu đề

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; // Nội dung phản ánh chi tiết hoặc nội dung thông báo

    @Column(name = "notification_type", length = 50)
    private String notificationType; // Phân loại thông báo (GENERAL/URGENT/FEE/MAINTENANCE)

    @Column(name = "target_type", length = 30)
    @Builder.Default
    private String targetType = "ALL";

    @Column(name = "target_id")
    private Long targetId; // ID đối tượng nhận thông báo cụ thể

    @Column(name = "is_published")
    @Builder.Default
    private Boolean isPublished = false;

    @Column(name = "published_at")
    private LocalDateTime publishedAt; // Thời điểm công bố thông báo

    @Column(name = "expires_at")
    private LocalDateTime expiresAt; // Thời điểm thông báo hết hạn

    @Column(name = "created_by", length = 100)
    private String createdBy; // Người tạo

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
