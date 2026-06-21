package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.admin.entity.Staff;

@Entity
@Table(name = "tec_inspection_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspectionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "checklist_id", nullable = false)
    private Checklist checklist; // Bảng checklist kỹ thuật liên quan
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspector_id")
    private Staff inspector; // Nhân viên thực hiện kiểm tra
    
    @Column(name = "inspect_date", nullable = false)
    private LocalDateTime inspectDate; // Thời điểm thực hiện kiểm tra

    @Column(name = "result", length = 20)
    private String result; // Kết quả kiểm tra (OK/WARNING/FAILED)

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // Ghi chú

    @Column(name = "image_url", length = 1000)
    private String imageUrl; // Đường dẫn ảnh minh chứng

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
