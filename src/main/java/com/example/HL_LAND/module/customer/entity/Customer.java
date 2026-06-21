package com.example.HL_LAND.module.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.system.entity.SysUser;
import com.example.HL_LAND.module.system.entity.enums.AccountStatus;

@Entity
@Table(name = "cus_customers", indexes = {
    @Index(name = "idx_customers_code", columnList = "customer_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "customer_code", unique = true, nullable = false, length = 50)
    private String customerCode;

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName; // Họ và tên

    @Column(name = "id_card", unique = true, length = 20)
    private String idCard; // Số CMND/CCCD/Hộ chiếu

    @Column(name = "phone", length = 20)
    private String phone; // Số điện thoại

    @Column(name = "email", length = 150)
    private String email; // Thư điện tử

    @Column(name = "address", columnDefinition = "TEXT")
    private String address; // Địa chỉ liên hệ

    @Column(name = "tax_code", length = 20)
    private String taxCode;

    @Column(name = "customer_type", length = 20)
    @Builder.Default
    private String customerType = "OWNER";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private AccountStatus status = AccountStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private SysUser user; // Tài khoản hệ thống liên kết

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}