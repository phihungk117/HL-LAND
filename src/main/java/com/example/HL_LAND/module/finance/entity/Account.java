package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "fin_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "account_no", nullable = false, length = 50)
    private String accountNo; // Số tài khoản ngân hàng thụ hưởng

    @Column(name = "bank_name", length = 200)
    private String bankName; // Tên ngân hàng thụ hưởng

    @Column(name = "account_name", length = 200)
    private String accountName; // Tên chủ tài khoản thụ hưởng

    @Column(name = "branch", length = 200)
    private String branch; // Chi nhánh ngân hàng thụ hưởng

    @Column(name = "is_default")
    @Builder.Default
    private Boolean isDefault = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
