package com.sp.spring.catalogservice.entity;

import com.sp.spring.catalogservice.util.DateAudit;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "REVIEW")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductReview extends DateAudit {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "REVIEW_ID", updatable = false, nullable = false)
    private String reviewId;

    @Column(name = "PRODUCT_ID", updatable = false, nullable = false)
    private String productId;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "RATING_VALUE", nullable = false)
    @Min(1)
    @Max(5)
    private double ratingValue;

    @Column(name = "REVIEW_MESSAGE")
    private String reviewMessage;

}