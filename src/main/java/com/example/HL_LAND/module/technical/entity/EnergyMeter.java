package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "tec_energy_meters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyMeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "meter_code", unique = true, nullable = false, length = 50)
    private String meterCode; // Mã công tơ / đồng hồ đo năng lượng

    @Column(name = "meter_name", nullable = false, length = 200)
    private String meterName; // Tên đồng hồ đo năng lượng

    @Column(name = "meter_type", length = 30)
    private String meterType; // Loại đồng hồ (ELECTRIC/WATER/GAS)

    @Column(name = "location", length = 300)
    private String location; // Vị trí lắp đặt

    @Column(name = "unit", length = 20)
    private String unit; // Đơn vị tính (VD: kWh, m3, phòng)

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}