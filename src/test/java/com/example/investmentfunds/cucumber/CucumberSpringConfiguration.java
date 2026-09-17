package com.example.investmentfunds.cucumber;

import com.example.investmentfunds.fund.controller.FundController;
import com.example.investmentfunds.fund.service.FundService;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@CucumberContextConfiguration
@WebMvcTest(FundController.class)
@Import(CucumberSpringConfiguration.MockConfiguration.class)
public class CucumberSpringConfiguration {
    @TestConfiguration
    static class MockConfiguration {
        @Bean
        FundService fundService() {
            return Mockito.mock(FundService.class);
        }
    }
}
