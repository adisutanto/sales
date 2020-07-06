package com.adisutanto.sales.customer;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/customers/countries")
    public List<String> findDistinctCountries() throws SQLException {
        log.info("findDistinctCountries");
        String sql = "SELECT DISTINCT country FROM customer WHERE country IS NOT NULL ORDER BY country";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}