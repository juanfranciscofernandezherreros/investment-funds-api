package com.example.investmentfunds.fund.controller;

import com.example.investmentfunds.fund.dto.CreateFundRequest;
import com.example.investmentfunds.fund.dto.FundResponse;
import com.example.investmentfunds.fund.dto.FundSearchFilter;
import com.example.investmentfunds.fund.dto.PageResponse;
import com.example.investmentfunds.fund.dto.PatchFundRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/funds")
public interface FundApi {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    FundResponse create(@RequestBody @Valid CreateFundRequest request);

    @GetMapping("/{id}")
    FundResponse findById(@PathVariable Long id);

    @GetMapping
    PageResponse<FundResponse> search(
            @ModelAttribute FundSearchFilter filter,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable);

    @PatchMapping("/{id}")
    FundResponse patch(@PathVariable Long id, @RequestBody @Valid PatchFundRequest request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
