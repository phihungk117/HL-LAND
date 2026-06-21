package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tec_checklists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "checklist_name", nullable = false, length = 300)
    private String checklistName; // Tên bảng kiểm tra kỹ thuật
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checkpoint_id", nullable = false)
    private Checkpoint checkpoint; // Điểm kiểm tra kỹ thuật liên quan
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Mô tả

    @Column(name = "frequency", length = 30)
    private String frequency; // Tần suất kiểm tra (DAILY/WEEKLY/MONTHLY)

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
