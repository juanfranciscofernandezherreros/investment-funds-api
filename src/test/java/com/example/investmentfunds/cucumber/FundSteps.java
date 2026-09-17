package com.example.investmentfunds.cucumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.investmentfunds.common.exception.*;
import com.example.investmentfunds.fund.model.Fund;
import com.example.investmentfunds.fund.service.FundService;
import io.cucumber.java.en.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

public class FundSteps {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FundService fundService;

    private MvcResult result;

    @Given("the fund service accepts a valid fund")
    public void validFund() {
        when(fundService.create(any())).thenReturn(fund());
    }

    @Given("the fund service cannot find fund {long}")
    public void missingFund(Long id) {
        when(fundService.findById(id)).thenThrow(new AppException(AppError.FUND_NOT_FOUND));
        doThrow(new AppException(AppError.FUND_NOT_FOUND)).when(fundService).delete(id);
    }

    @Given("the fund service returns a page of funds")
    public void pageFunds() {
        when(fundService.search(any(), any())).thenReturn(new PageImpl<>(List.of(fund()), PageRequest.of(0, 20), 1));
    }

    @Given("the fund service accepts an updated fund")
    public void updatedFund() {
        when(fundService.patch(eq(1L), any())).thenReturn(fund());
    }

    @When("I send a POST request to {string} with body {string}")
    public void post(String path, String body) throws Exception {
        result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        trace("POST", path, body);
    }

    @When("I send a PATCH request to {string} with body {string}")
    public void patch(String path, String body) throws Exception {
        result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andReturn();
        trace("PATCH", path, body);
    }

    @When("I send a GET request to {string}")
    public void get(String path) throws Exception {
        result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get(path))
                .andReturn();
        trace("GET", path, "");
    }

    @When("I send a DELETE request to {string}")
    public void delete(String path) throws Exception {
        result = mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete(path))
                .andReturn();
        trace("DELETE", path, "");
    }

    @Then("the HTTP response status is {int}")
    public void status(int expected) {
        assertThat(result.getResponse().getStatus()).isEqualTo(expected);
    }

    @Then("the response contains {string}")
    public void contains(String value) throws Exception {
        assertThat(result.getResponse().getContentAsString()).contains(value);
    }

    private void trace(String method, String path, String body) throws Exception {
        System.out.printf(
                "REQUEST %s %s %s%nRESPONSE %d %s%n",
                method,
                path,
                body,
                result.getResponse().getStatus(),
                result.getResponse().getContentAsString());
    }

    private Fund fund() {
        return Fund.builder()
                .withId(1L)
                .withIsin("ES0000000001")
                .withName("Fund")
                .withCurrency("EUR")
                .withActive(true)
                .build();
    }
}
