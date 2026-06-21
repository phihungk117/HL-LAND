package com.example.HL_LAND.module.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cus_apartment_owners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApartmentOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id", nullable = false)
    private Apartment apartment; // Căn hộ liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Khách hàng liên kết
    
    @Column(name = "ownership_type", length = 20)
    @Builder.Default
    private String ownershipType = "OWNER";

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate; // Ngày bắt đầu

    @Column(name = "to_date")
    private LocalDate toDate; // Ngày kết thúc

    @Column(name = "is_current")
    @Builder.Default
    private Boolean isCurrent = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
