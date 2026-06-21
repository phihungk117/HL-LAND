package com.example.HL_LAND.module.technical.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "tec_energy_readings", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"meter_id", "reading_date"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_id", nullable = false)
    private EnergyMeter meter; // Đồng hồ đo năng lượng liên kết
    
    @Column(name = "reading_date", nullable = false)
    private LocalDate readingDate; // Ngày ghi nhận chỉ số

    @Column(name = "reading_value", nullable = false, precision = 15, scale = 4)
    private BigDecimal readingValue; // Giá trị chỉ số ghi nhận

    @Column(name = "recorded_by", length = 100)
    private String recordedBy; // Người ghi nhận chỉ số

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // Ghi chú

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
