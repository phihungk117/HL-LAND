package com.example.HL_LAND.module.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cus_residents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment; // Căn hộ liên kết
    
    @Column(name = "full_name", nullable = false, length = 200)
    private String fullName; // Họ và tên

    @Column(name = "id_card", length = 20)
    private String idCard; // Số CMND/CCCD/Hộ chiếu

    @Column(name = "phone", length = 20)
    private String phone; // Số điện thoại

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth; // Ngày sinh

    @Column(name = "gender", length = 10)
    private String gender; // Giới tính

    @Column(name = "relation", length = 50)
    private String relation; // Mối quan hệ với chủ hộ hoặc nhân viên

    @Column(name = "move_in_date")
    private LocalDate moveInDate; // Ngày chuyển vào căn hộ

    @Column(name = "move_out_date")
    private LocalDate moveOutDate; // Ngày dọn đi khỏi căn hộ

    @Column(name = "is_current")
    @Builder.Default
    private Boolean isCurrent = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
