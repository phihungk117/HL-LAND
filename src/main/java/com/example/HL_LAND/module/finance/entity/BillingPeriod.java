package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.finance.entity.enums.BillingPeriodStatus;

@Entity
@Table(name = "fin_billing_periods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BillingPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "period_code", unique = true, nullable = false, length = 20)
    private String periodCode; // Mã kỳ tính phí (Ví dụ: 2024-01)

    @Column(name = "year", nullable = false)
    private Integer year; // Năm tốt nghiệp hoặc Năm áp dụng

    @Column(name = "month", nullable = false)
    private Integer month;

    @Column(name = "from_date")
    private LocalDate fromDate; // Ngày bắt đầu

    @Column(name = "to_date")
    private LocalDate toDate; // Ngày kết thúc
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private BillingPeriodStatus status = BillingPeriodStatus.OPEN;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
