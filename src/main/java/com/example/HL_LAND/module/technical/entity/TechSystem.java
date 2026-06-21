package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tec_systems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "system_code", unique = true, nullable = false, length = 50)
    private String systemCode; // Mã hệ thống kỹ thuật (PCCC, ELEVATOR...)

    @Column(name = "system_name", nullable = false, length = 300)
    private String systemName; // Tên hệ thống kỹ thuật

    @Column(name = "system_type", length = 100)
    private String systemType; // Phân loại hệ thống kỹ thuật

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Mô tả

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
