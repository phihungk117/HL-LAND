package com.example.HL_LAND.module.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import com.example.HL_LAND.module.system.entity.enums.ApartmentStatus;

@Entity
@Table(name = "cus_apartments", indexes = {
    @Index(name = "idx_apartments_code", columnList = "apartment_code")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "apartment_code", unique = true, nullable = false, length = 50)
    private String apartmentCode; // Mã căn hộ

    @Column(name = "block", length = 20)
    private String block; // Tên tòa/block căn hộ

    @Column(name = "floor")
    private Integer floor; // Tầng chứa căn hộ

    @Column(name = "apartment_no", length = 20)
    private String apartmentNo; // Số phòng căn hộ

    @Column(name = "area", precision = 10, scale = 2)
    private BigDecimal area; // Diện tích căn hộ (m2)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    @Builder.Default
    private ApartmentStatus status = ApartmentStatus.OCCUPIED;

    @Column(name = "handover_date")
    private LocalDate handoverDate; // Ngày bàn giao căn hộ

    @Column(name = "invoice_name", length = 200)
    private String invoiceName; // Tên xuất hóa đơn căn hộ

    @Column(name = "invoice_address", columnDefinition = "TEXT")
    private String invoiceAddress; // Địa chỉ ghi trên hóa đơn

    @Column(name = "invoice_tax", length = 20)
    private String invoiceTax; // Mã số thuế hóa đơn

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resident> residents;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApartmentOwner> owners;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}
