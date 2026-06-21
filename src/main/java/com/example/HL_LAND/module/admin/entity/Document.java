package com.example.HL_LAND.module.admin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "adm_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng

    @Column(name = "title", nullable = false, length = 500)
    private String title; // Tiêu đề

    @Column(name = "doc_type", length = 50)
    private String docType;

    @Column(name = "file_url", length = 1000)
    private String fileUrl;

    @Column(name = "file_name", length = 300)
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Mô tả

    @Column(name = "created_by", length = 100)
    private String createdBy; // Người tạo

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Thời điểm tạo

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // Thời điểm cập nhật
}