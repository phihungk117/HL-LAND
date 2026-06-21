package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.system.entity.enums.AccountStatus;

@Entity
@Table(name = "tec_equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "equipment_code", unique = true, nullable = false, length = 50)
    private String equipmentCode; // Mã thiết bị kỹ thuật

    @Column(name = "equipment_name", nullable = false, length = 300)
    private String equipmentName; // Tên thiết bị kỹ thuật
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id")
    private TechSystem system; // Hệ thống kỹ thuật liên kết
    
    @Column(name = "location", length = 300)
    private String location; // Vị trí lắp đặt

    @Column(name = "brand", length = 100)
    private String brand; // Thương hiệu

    @Column(name = "model", length = 100)
    private String model; // Model/Dòng thiết bị

    @Column(name = "serial_no", length = 100)
    private String serialNo; // Số serial thiết bị

    @Column(name = "install_date")
    private LocalDate installDate; // Ngày lắp đặt thiết bị

    @Column(name = "warranty_date")
    private LocalDate warrantyDate; // Ngày hết hạn bảo hành
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private AccountStatus status = AccountStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
