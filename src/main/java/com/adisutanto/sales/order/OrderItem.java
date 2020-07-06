package com.adisutanto.sales.order;

import com.adisutanto.sales.product.Product;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @NotNull
    @Positive
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
