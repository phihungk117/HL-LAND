package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "fin_fee_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "fee_code", unique = true, nullable = false, length = 50)
    private String feeCode; // Mã loại phí (VD: ELECTRIC, WATER)

    @Column(name = "fee_name", nullable = false, length = 200)
    private String feeName; // Tên loại phí

    @Column(name = "fee_category", length = 50)
    private String feeCategory; // Danh mục phí (WATER/ELECTRIC/GAS/MGMT...)

    @Column(name = "unit", length = 50)
    private String unit; // Đơn vị tính (VD: kWh, m3, phòng)

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Mô tả

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
