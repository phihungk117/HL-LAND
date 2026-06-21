package com.example.HL_LAND.module.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.customer.entity.Apartment;
import com.example.HL_LAND.module.customer.entity.Customer;
import com.example.HL_LAND.common.enums.ApprovalStatus;

@Entity
@Table(name = "app_registrations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "reg_no", unique = true, nullable = false, length = 50)
    private String regNo; // Mã số đăng ký tiện ích/ra vào (VD: DK001)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment; // Căn hộ liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; // Khách hàng liên kết
    
    @Column(name = "reg_type", length = 50)
    private String regType; // Loại đăng ký (GOODS_IN/GOODS_OUT/CONSTRUCTION...)

    @Column(name = "from_date")
    private LocalDateTime fromDate; // Ngày bắt đầu

    @Column(name = "to_date")
    private LocalDateTime toDate; // Ngày kết thúc

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Mô tả
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private ApprovalStatus status = ApprovalStatus.PENDING;
    
    @Column(name = "approved_by", length = 100)
    private String approvedBy; // Người phê duyệt đăng ký

    @Column(name = "approved_at")
    private LocalDateTime approvedAt; // Thời điểm phê duyệt đăng ký

    @Column(name = "reject_reason", columnDefinition = "TEXT")
    private String rejectReason; // Lý do từ chối phê duyệt

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}
