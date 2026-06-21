package com.example.HL_LAND.module.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.system.entity.enums.AccountStatus;

@Entity
@Table(name = "cus_vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment; // Căn hộ liên kết
    
    @Column(name = "license_plate", nullable = false, length = 20)
    private String licensePlate; // Biển kiểm soát phương tiện

    @Column(name = "vehicle_type", length = 20)
    private String vehicleType; // Loại phương tiện (MOTORBIKE/CAR/BICYCLE)

    @Column(name = "brand", length = 100)
    private String brand; // Thương hiệu

    @Column(name = "color", length = 50)
    private String color; // Màu sắc
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private AccountStatus status = AccountStatus.ACTIVE;
    
    @Column(name = "from_date")
    private LocalDate fromDate; // Ngày bắt đầu

    @Column(name = "to_date")
    private LocalDate toDate; // Ngày kết thúc

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
