package com.example.HL_LAND.module.system.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sys_building_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysBuildingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "project_name", length = 300, nullable = false)
    private String projectName; // Tên dự án/tòa nhà
    @Column(name = "address", columnDefinition = "TEXT")
    private String address; // Địa chỉ liên hệ
    @Column(name = "hotline", length = 50)
    private String hotline; // Đường dây nóng liên hệ
    @Column(name = "mgmt_fee_rate", precision = 10, scale = 4)
    private BigDecimal mgmtFeeRate; // Đơn giá phí quản lý

    @Column(name = "vat_rate", precision = 5, scale = 4)
    @Builder.Default
    private BigDecimal vatRate = new BigDecimal("0.1000");

    @Column(name = "bank_account", length = 100)
    private String bankAccount; // Số tài khoản ngân hàng thụ hưởng

    @Column(name = "bank_name", length = 200)
    private String bankName; // Tên ngân hàng thụ hưởng

    @Column(name = "payment_note", columnDefinition = "TEXT")
    private String paymentNote; // Ghi chú thanh toán mặc định

    @Column(name = "logo_url", length = 500)
    private String logoUrl; // Đường dẫn ảnh logo tòa nhà

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}
