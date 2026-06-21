package com.example.HL_LAND.module.system.entity;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sys_refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysRefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    // Mapping Khóa ngoại (Foreign Key) REFERENCES sys_users(id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private SysUser user; // Tài khoản hệ thống liên kết

    @Column(name = "token", columnDefinition = "TEXT", nullable = false, unique = true)
    private String token; // Token làm mới (Refresh Token)

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt; // Thời điểm thông báo hết hạn

    @Column(name = "revoked")
    @Builder.Default
    private Boolean revoked = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

}
