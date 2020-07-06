package com.adisutanto.sales.order;

import com.adisutanto.sales.customer.Customer;
import com.adisutanto.sales.product.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@EqualsAndHashCode(of = "orderNumber")
@Entity
@Table(name = "customer_order")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NaturalIdCache
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @NotNull
    @Size(max = 10)
    @NaturalId
    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> products = new ArrayList<>();

    public void addProduct(Product product) {
        OrderItem orderItem = new OrderItem(this, product);
        products.add(orderItem);
    }

    public void removeProduct(Product product) {
        for (Iterator<OrderItem> iterator = products.iterator(); iterator.hasNext();) {
            OrderItem orderItem = iterator.next();
            if (orderItem.getOrder().equals(this) && orderItem.getProduct().equals(product)) {
                iterator.remove();
                orderItem.setOrder(null);
                orderItem.setProduct(null);
            }
        }
    }
}
