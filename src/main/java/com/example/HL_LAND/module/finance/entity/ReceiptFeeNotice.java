package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fin_receipt_fee_notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptFeeNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt; // Phiếu thu/chi liên quan
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fee_notice_id", nullable = false)
    private FeeNotice feeNotice; // Thông báo phí liên quan
    
    @Column(name = "paid_amount", nullable = false, precision = 15, scale = 2)
    private java.math.BigDecimal paidAmount;
}