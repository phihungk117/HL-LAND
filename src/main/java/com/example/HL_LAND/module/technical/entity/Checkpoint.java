package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tec_checkpoints")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Checkpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "checkpoint_code", unique = true, nullable = false, length = 50)
    private String checkpointCode; // Mã điểm kiểm tra kỹ thuật

    @Column(name = "checkpoint_name", nullable = false, length = 300)
    private String checkpointName; // Tên điểm kiểm tra kỹ thuật

    @Column(name = "location", length = 300)
    private String location; // Vị trí lắp đặt
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private TechSystem system; // Hệ thống kỹ thuật liên kết
    
    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
