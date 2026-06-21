package com.example.HL_LAND.module.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.system.entity.SysUser;
import com.example.HL_LAND.module.system.entity.enums.AccountStatus;

@Entity
@Table(name = "adm_staff")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private SysUser user; // Tài khoản hệ thống liên kết

    @Column(name = "employee_code", unique = true, length = 50)
    private String employeeCode; // Mã nhân viên

    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName; // Họ và tên

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Ngày sinh

    @Column(name = "gender", length = 10)
    private String gender; // Giới tính

    @Column(name = "id_card", unique = true, length = 20)
    private String idCard; // Số CMND/CCCD/Hộ chiếu

    @Column(name = "phone", length = 20)
    private String phone; // Số điện thoại

    @Column(name = "email", length = 150)
    private String email; // Thư điện tử

    @Column(name = "address", columnDefinition = "TEXT")
    private String address; // Địa chỉ liên hệ

    @Column(name = "department", length = 100)
    private String department; // Phòng ban làm việc

    @Column(name = "position", length = 100)
    private String position; // Chức vụ đảm nhiệm

    @Column(name = "hire_date")
    private LocalDate hireDate; // Ngày tuyển dụng
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private AccountStatus status = AccountStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}
