package com.adisutanto.sales;

import com.adisutanto.sales.customer.Customer;
import com.adisutanto.sales.order.Order;
import com.adisutanto.sales.order.OrderItem;
import com.adisutanto.sales.product.Product;
import com.adisutanto.sales.supplier.Supplier;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Customer.class, Product.class, Supplier.class, Order.class, OrderItem.class);
    }
}