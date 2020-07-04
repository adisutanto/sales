package com.adisutanto.sales.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class ReportService {
    private final JdbcTemplate jdbcTemplate;

    @Value("${app.top-n}")
    private int topN;
    @Value("${app.n-month}")
    private int nMonth;

    @Autowired
    public ReportService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ProductSales> topSellingProducts(String country) {
        String sql = "SELECT p.product_name, SUM(i.quantity) AS sales FROM product p" +
                " INNER JOIN order_item i ON i.product_id = p.id" +
                " INNER JOIN customer_order o ON i.order_id = o.id" +
                " INNER JOIN customer c ON o.customer_id = c.id" +
                (country == null ? "" : " WHERE c.country = ?") +
                " GROUP BY p.id" +
                " ORDER BY sales DESC, p.id" +
                " LIMIT " + topN;
        return country == null ? jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ProductSales.class)) :
                jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ProductSales.class), country);
    }

    public List<YearMonthSales> salesByMonth(String country) {
        String sql = "SELECT * from (" +
                " SELECT YEAR(o.order_date) AS year, MONTH(o.order_date) AS month, SUM(i.quantity) AS sales" +
                " FROM order_item i" +
                " INNER JOIN customer_order o ON i.order_id = o.id" +
                " INNER JOIN customer c ON o.customer_id = c.id" +
                (country == null ? "" : " WHERE c.country = ?") +
                " GROUP BY year, month" +
                " ORDER BY year DESC, month DESC" +
                " LIMIT " + nMonth +
                ") in ORDER BY in.year, in.month";
        return country == null ? jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(YearMonthSales.class)) :
                jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(YearMonthSales.class), country);
    }
}
