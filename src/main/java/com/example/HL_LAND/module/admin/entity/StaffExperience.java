package com.example.HL_LAND.module.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "adm_staff_experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    
    @Column(length = 300)
    private String company; // Công ty làm việc trước đó
    @Column(length = 100)
    private String position; // Chức vụ đảm nhiệm
    @Column(name = "from_date")
    private LocalDate fromDate; // Ngày bắt đầu
    @Column(name = "to_date")
    private LocalDate toDate; // Ngày kết thúc
    @Column(columnDefinition = "TEXT")
    private String description; // Mô tả
}