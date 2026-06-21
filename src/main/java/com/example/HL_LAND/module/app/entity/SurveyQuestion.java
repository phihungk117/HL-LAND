package com.example.HL_LAND.module.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_survey_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Khóa chính tự tăng
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey; // Khảo sát liên kết
    
    @Column(name = "question_text", nullable = false, columnDefinition = "TEXT")
    private String questionText; // Nội dung câu hỏi khảo sát
    @Column(name = "question_type", length = 20)
    private String questionType; // Loại câu hỏi (RATING/TEXT/CHOICE)
    @Column(columnDefinition = "TEXT")
    private String options; // Các tùy chọn lựa chọn (dạng mảng JSON)
    @Column(name = "sort_order")
    private Integer sortOrder; // Thứ tự sắp xếp câu hỏi
}
