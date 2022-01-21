package com.learn.examFirst.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrdersEntity {


    @Column(columnDefinition = "BINARY(16)")
    private UUID requestID;

    @Id
    @Column(length = 8)
    private String orderId;

    private String productId;

    private LocalDateTime date1;

    private String orderName;

    private LocalDateTime requestCreatedTime;

}
