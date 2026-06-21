package com.example.HL_LAND.module.finance.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.example.HL_LAND.module.customer.entity.Apartment;
import com.example.HL_LAND.module.customer.entity.Customer;

@Entity
@Table(name = "fin_receipts", indexes = {
    @Index(name = "idx_receipts_date", columnList = "payment_date")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "receipt_no", unique = true, nullable = false, length = 50)
    private String receiptNo; // Số phiếu thu/chi (VD: PT001)

    @Column(name = "receipt_type", nullable = false, length = 10)
    private String receiptType; // Loại phiếu: IN (Thu) hoặc OUT (Chi)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment; // Căn hộ liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; // Khách hàng liên kết
    
    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount; // Số tiền trước thuế

    @Column(name = "payment_method", length = 30)
    private String paymentMethod; // Phương thức thanh toán (CASH, BANK_TRANSFER...)

    @Column(name = "payment_date", nullable = false)
    private LocalDateTime paymentDate; // Ngày giờ thực hiện giao dịch

    @Column(name = "reason", length = 500)
    private String reason; // Diễn giải lý do thu/chi

    @Column(name = "bank_account", length = 100)
    private String bankAccount; // Số tài khoản ngân hàng thụ hưởng

    @Column(name = "reference_no", length = 100)
    private String referenceNo; // Mã giao dịch đối soát ngân hàng

    @Column(name = "note", columnDefinition = "TEXT")
    private String note; // Ghi chú

    @Column(name = "created_by", length = 100)
    private String createdBy; // Người tạo

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo
}
