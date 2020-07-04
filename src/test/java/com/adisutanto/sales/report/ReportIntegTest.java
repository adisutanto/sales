package com.adisutanto.sales.report;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportIntegTest {
    @Value("${app.top-n}")
    private int topN;
    @Value("${app.n-month}")
    private int nMonth;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void topSellingProductsWithoutCountry() throws Exception {
        ResponseEntity<ProductSales[]> responseEntity = restTemplate.getForEntity("/report/top-selling-products",
                ProductSales[].class);
        ProductSales[] productSales = responseEntity.getBody();
        assertThat(productSales.length).isEqualTo(topN);
        assertThat(productSales[0].getProductName()).isEqualTo("Soup - Clam Chowder, Dry Mix");
        assertThat(productSales[0].getSales()).isEqualTo(102);
        assertThat(productSales[topN - 1].getProductName()).isEqualTo("Crackers - Water");
        assertThat(productSales[topN - 1].getSales()).isEqualTo(73);
    }

    @Test
    public void topSellingProductsWithCountry() throws Exception {
        Map<String, String> urlVariables = Map.of("country", "Indonesia");
        ResponseEntity<ProductSales[]> responseEntity = restTemplate.getForEntity(
                "/report/top-selling-products?country={country}", ProductSales[].class, urlVariables);
        ProductSales[] productSales = responseEntity.getBody();
        assertThat(productSales.length).isEqualTo(topN);
        assertThat(productSales[0].getProductName()).isEqualTo("Beer - Blue Light");
        assertThat(productSales[0].getSales()).isEqualTo(22);
        assertThat(productSales[topN - 1].getProductName()).isEqualTo("Coke - Diet, 355 Ml");
        assertThat(productSales[topN - 1].getSales()).isEqualTo(9);
    }

    @Test
    public void salesByMonthWithoutCountry() throws Exception {
        ResponseEntity<YearMonthSales[]> responseEntity = restTemplate.getForEntity(
                "/report/sales-by-month", YearMonthSales[].class);
        YearMonthSales[] sales = responseEntity.getBody();
        assertThat(sales.length).isEqualTo(nMonth);
        assertThat(sales[0].getYear()).isEqualTo(2019);
        assertThat(sales[0].getMonth()).isEqualTo(8);
        assertThat(sales[0].getSales()).isEqualTo(228);
        assertThat(sales[nMonth - 1].getYear()).isEqualTo(2020);
        assertThat(sales[nMonth - 1].getMonth()).isEqualTo(7);
        assertThat(sales[nMonth - 1].getSales()).isEqualTo(70);
    }

    @Test
    public void salesByMonthWithCountry() throws Exception {
        Map<String, String> urlVariables = Map.of("country", "Indonesia");
        ResponseEntity<YearMonthSales[]> responseEntity = restTemplate.getForEntity(
                "/report/sales-by-month?country={country}", YearMonthSales[].class, urlVariables);
        YearMonthSales[] sales = responseEntity.getBody();
        assertThat(sales.length).isEqualTo(5);
        assertThat(sales[0].getYear()).isEqualTo(2019);
        assertThat(sales[0].getMonth()).isEqualTo(9);
        assertThat(sales[0].getSales()).isEqualTo(83);
        assertThat(sales[4].getYear()).isEqualTo(2020);
        assertThat(sales[4].getMonth()).isEqualTo(3);
        assertThat(sales[4].getSales()).isEqualTo(45);
    }

}
