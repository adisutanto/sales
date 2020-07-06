package com.adisutanto.sales.product;

import com.adisutanto.sales.supplier.Supplier;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 50)
    @Column(name = "product_name")
    private String productName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    @NotNull
    private Supplier supplier;
    @NotNull
    @Digits(integer = 12, fraction = 2)
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "package")
    @Size(max = 30)
    private String pkg;
    @NotNull
    @Column(name = "is_discontinued")
    private Boolean isDiscontinued;

}
