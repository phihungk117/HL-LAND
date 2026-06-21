package com.example.HL_LAND.module.admin.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "adm_staff_family")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;
    @Column(name = "full_name", length = 200)
    private String fullName; // Họ và tên
    @Column(length = 50)
    private String relation; // Mối quan hệ với chủ hộ hoặc nhân viên
    @Column(length = 20)
    private String phone; // Số điện thoại
    @Column(name = "id_card", length = 20)
    private String idCard; // Số CMND/CCCD/Hộ chiếu
}
