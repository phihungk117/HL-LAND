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
    private Long id;

    @Column(name = "project_name", length = 300, nullable = false)
    private String projectName;
    @Column(name = "address", columnDefinition = "TEXT")
    private String address;
    @Column(name = "hotline", length = 50)
    private String hotline;
    @Column(name = "mgmt_fee_rate", precision = 10, scale = 4)
    private BigDecimal mgmtFeeRate;

    @Column(name = "vat_rate", precision = 5, scale = 4)
    @Builder.Default
    private BigDecimal vatRate = new BigDecimal("0.1000");

    @Column(name = "bank_account", length = 100)
    private String bankAccount;

    @Column(name = "bank_name", length = 200)
    private String bankName;

    @Column(name = "payment_note", columnDefinition = "TEXT")
    private String paymentNote;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
