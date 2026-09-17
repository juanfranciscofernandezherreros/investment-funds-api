package com.example.investmentfunds.fund.controller;

import com.example.investmentfunds.fund.dto.*;
import com.example.investmentfunds.fund.mapper.FundMapper;
import com.example.investmentfunds.fund.service.FundService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/funds")
@RequiredArgsConstructor
public class FundController {
    private final FundService fundService;

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public FundResponse create(@RequestBody @Valid CreateFundRequest request) {
        var fund = FundMapper.fromCreateRequest(request);
        var created = fundService.create(fund);
        var response = FundMapper.toResponse(created);
        return response;
    }

    @GetMapping("/{id}")
    public FundResponse findById(@PathVariable Long id) {
        var fund = fundService.findById(id);
        var response = FundMapper.toResponse(fund);
        return response;
    }

    @GetMapping
    public PageResponse<FundResponse> search(
            @ModelAttribute FundSearchFilter filter,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        var funds = fundService.search(filter, pageable);
        List<FundResponse> content = funds.map(FundMapper::toResponse).getContent();
        var response = new PageResponse<>(
                content, funds.getNumber(), funds.getSize(), funds.getTotalElements(), funds.getTotalPages());
        return response;
    }

    @PatchMapping("/{id}")
    public FundResponse patch(@PathVariable Long id, @RequestBody @Valid PatchFundRequest request) {
        var patch = FundMapper.fromPatchRequest(request);
        var updated = fundService.patch(id, patch);
        var response = FundMapper.toResponse(updated);
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fundService.delete(id);
    }
}
