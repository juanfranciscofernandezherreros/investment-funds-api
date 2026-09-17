package com.example.investmentfunds.fund.controller;

import com.example.investmentfunds.fund.dto.*;
import com.example.investmentfunds.fund.mapper.FundMapper;
import com.example.investmentfunds.fund.service.FundService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FundController implements FundApi {
    private final FundService fundService;

    @Override
    public FundResponse create(CreateFundRequest request) {
        var fund = FundMapper.fromCreateRequest(request);
        var created = fundService.create(fund);
        var response = FundMapper.toResponse(created);
        return response;
    }

    @Override
    public FundResponse findById(@PathVariable Long id) {
        var fund = fundService.findById(id);
        var response = FundMapper.toResponse(fund);
        return response;
    }

    @Override
    public PageResponse<FundResponse> search(FundSearchFilter filter, Pageable pageable) {
        var funds = fundService.search(filter, pageable);
        List<FundResponse> content = funds.map(FundMapper::toResponse).getContent();
        var response = new PageResponse<>(
                content, funds.getNumber(), funds.getSize(), funds.getTotalElements(), funds.getTotalPages());
        return response;
    }

    @Override
    public FundResponse patch(Long id, PatchFundRequest request) {
        var patch = FundMapper.fromPatchRequest(request);
        var updated = fundService.patch(id, patch);
        var response = FundMapper.toResponse(updated);
        return response;
    }

    @Override
    public void delete(@PathVariable Long id) {
        fundService.delete(id);
    }
}
