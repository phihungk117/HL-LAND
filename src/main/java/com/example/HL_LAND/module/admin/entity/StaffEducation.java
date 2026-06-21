package com.example.HL_LAND.module.admin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adm_staff_education")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @Column(length = 100)
    private String degree; // Bằng cấp học vấn
    @Column(length = 200)
    private String major; // Chuyên ngành đào tạo
    @Column(length = 300)
    private String school; // Trường học đào tạo
    private Integer year; // Năm tốt nghiệp hoặc Năm áp dụng
}
