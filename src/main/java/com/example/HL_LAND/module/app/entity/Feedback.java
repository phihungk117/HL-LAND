package com.example.HL_LAND.module.app.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

import com.example.HL_LAND.module.customer.entity.Apartment;
import com.example.HL_LAND.module.customer.entity.Customer;
import com.example.HL_LAND.module.customer.entity.enums.TicketStatus;

@Entity
@Table(name = "app_feedbacks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "feedback_no", unique = true, nullable = false, length = 50)
    private String feedbackNo; // Mã số phản ánh (VD: PA001)
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private Apartment apartment; // Căn hộ liên kết
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer; // Khách hàng liên kết
    
    @Column(name = "feedback_type", length = 30)
    private String feedbackType; // Loại phản ánh (FEEDBACK/EXCHANGE/SUPPORT)

    @Column(name = "title", length = 500)
    private String title; // Tiêu đề

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content; // Nội dung phản ánh chi tiết hoặc nội dung thông báo
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    @Builder.Default
    private TicketStatus status = TicketStatus.PENDING;
    
    @Column(name = "response", columnDefinition = "TEXT")
    private String response;

    @Column(name = "responded_by", length = 100)
    private String respondedBy; // Người phản hồi của ban quản lý

    @Column(name = "responded_at")
    private LocalDateTime respondedAt; // Thời điểm ban quản lý phản hồi

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}