package com.adisutanto.sales.report;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/top-selling-products")
    public List<ProductSales> topSellingProducts(@RequestParam(name = "country", required = false) String country) {
        log.info("topSellngProducts, country={}", country);
        return reportService.topSellingProducts(country);
    }

    @GetMapping("/report/sales-by-month")
    public List<YearMonthSales> salesByMonth(@RequestParam(name = "country", required = false) String country) {
        log.info("salesByMonth, country={}", country);
        return reportService.salesByMonth(country);
    }
}
