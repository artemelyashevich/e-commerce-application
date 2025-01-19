package org.elyashevich.ecommerce.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
