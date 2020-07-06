package com.adisutanto.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @RestController
@SpringBootApplication
public class SalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SalesApplication.class, args);
    }

    // @Configuration
    // @Order(SecurityProperties.BASIC_AUTH_ORDER)
    // protected static class SecurityConfiguration extends
    // WebSecurityConfigurerAdapter {
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.httpBasic().and().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
    // }
    // }

    // @RequestMapping("/user")
    // public Principal user(Principal user) {
    // return user;
    // }
}
