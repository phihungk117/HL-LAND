package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.example.HL_LAND.module.customer.entity.Apartment;
import com.example.HL_LAND.module.finance.entity.enums.FeeStatus;

@Entity
@Table(name = "fin_fee_notices", indexes = {
    @Index(name = "idx_fee_notices_apartment", columnList = "apartment_id"),
    @Index(name = "idx_fee_notices_period", columnList = "period_id"),
    @Index(name = "idx_fee_notices_status", columnList = "status")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "notice_no", unique = true, nullable = false, length = 50)
    private String noticeNo; // Số/mã thông báo phí
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment; // Căn hộ liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", nullable = false)
    private BillingPeriod period; // Kỳ tính phí liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_type_id", nullable = false)
    private FeeType feeType; // Loại phí liên kết
    
    @Column(name = "usage_start", precision = 10, scale = 2)
    private BigDecimal usageStart; // Chỉ số sử dụng đầu

    @Column(name = "usage_end", precision = 10, scale = 2)
    private BigDecimal usageEnd; // Chỉ số sử dụng cuối

    @Column(name = "usage_amount", precision = 10, scale = 2)
    private BigDecimal usageAmount; // Số lượng tiêu thụ thực tế

    @Column(name = "unit_price", precision = 15, scale = 4)
    private BigDecimal unitPrice; // Đơn giá áp dụng

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount; // Số tiền trước thuế

    @Column(name = "vat_amount", precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal vatAmount = BigDecimal.ZERO;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount; // Tổng số tiền phải thanh toán
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private FeeStatus status = FeeStatus.UNPAID;
    
    @Column(name = "due_date")
    private LocalDate dueDate; // Hạn cuối nộp tiền

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // Ghi chú

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}