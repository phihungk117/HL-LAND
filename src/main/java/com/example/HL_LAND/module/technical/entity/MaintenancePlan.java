package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.example.HL_LAND.module.technical.entity.enums.MaintenanceStatus;

@Entity
@Table(name = "tec_maintenance_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenancePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "plan_code", unique = true, nullable = false, length = 50)
    private String planCode; // Mã kế hoạch bảo trì

    @Column(name = "plan_name", nullable = false, length = 300)
    private String planName; // Tên kế hoạch bảo trì
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private TechSystem system; // Hệ thống kỹ thuật liên kết
    
    @Column(name = "tasks", columnDefinition = "TEXT")
    private String tasks; // Các công việc bảo trì cần thực hiện

    @Column(name = "executor", length = 200)
    private String executor; // Đơn vị thực hiện bảo trì (Nội bộ / Nhà thầu)

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate; // Ngày dự kiến thực hiện bảo trì

    @Column(name = "actual_date")
    private LocalDate actualDate; // Ngày thực tế thực hiện bảo trì
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private MaintenanceStatus status = MaintenanceStatus.PLANNED; 
    
    @Column(name = "cost", precision = 15, scale = 2)
    private BigDecimal cost; // Chi phí thực hiện kế hoạch bảo trì

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // Ghi chú

    @Column(name = "created_by", length = 100)
    private String createdBy; // Người tạo

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}