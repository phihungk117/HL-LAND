package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "fin_fee_configs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_type_id", nullable = false)
    private FeeType feeType; // Loại phí liên kết
    
    @Column(name = "min_usage", precision = 10, scale = 2)
    private BigDecimal minUsage; // Mức sử dụng tối thiểu

    @Column(name = "max_usage", precision = 10, scale = 2)
    private BigDecimal maxUsage; // Mức sử dụng tối đa

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 4)
    private BigDecimal unitPrice; // Đơn giá áp dụng

    @Column(name = "apply_from", nullable = false)
    private LocalDate applyFrom; // Ngày bắt đầu áp dụng đơn giá

    @Column(name = "apply_to")
    private LocalDate applyTo; // Ngày kết thúc áp dụng đơn giá

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
