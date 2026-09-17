package com.example.investmentfunds.fund.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.investmentfunds.common.exception.*;
import com.example.investmentfunds.fund.model.Fund;
import com.example.investmentfunds.fund.service.FundService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(FundController.class)
@AutoConfigureMockMvc(addFilters = false)
class FundControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FundService fundService;

    @Test
    void create_when_valid_returns_created_ok() throws Exception {
        when(fundService.create(any())).thenReturn(fund());
        mockMvc.perform(post("/api/v1/funds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isin\":\"ES0000000001\",\"name\":\"Fund\",\"currency\":\"EUR\",\"active\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void create_when_invalid_returns_bad_request_ko() throws Exception {
        mockMvc.perform(post("/api/v1/funds")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isin\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void get_when_missing_returns_not_found_ko() throws Exception {
        when(fundService.findById(1L)).thenThrow(new AppException(AppError.FUND_NOT_FOUND));
        mockMvc.perform(get("/api/v1/funds/1")).andExpect(status().isNotFound());
    }

    @Test
    void search_returns_page_ok() throws Exception {
        when(fundService.search(any(), any())).thenReturn(new PageImpl<>(List.of(fund()), PageRequest.of(0, 20), 1));
        mockMvc.perform(get("/api/v1/funds").param("currency", "EUR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    void patch_returns_updated_ok() throws Exception {
        when(fundService.patch(eq(1L), any())).thenReturn(fund());
        mockMvc.perform(patch("/api/v1/funds/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Changed\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fund"));
    }

    @Test
    void delete_returns_no_content_ok() throws Exception {
        mockMvc.perform(delete("/api/v1/funds/1")).andExpect(status().isNoContent());
        verify(fundService).delete(1L);
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
