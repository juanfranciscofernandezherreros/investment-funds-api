package com.example.investmentfunds.fund.controller;

import com.example.investmentfunds.fund.dto.FundSearchFilter;
import com.example.investmentfunds.fund.service.FundService;
import com.example.investmentfunds.generated.api.FundsApi;
import com.example.investmentfunds.generated.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FundController implements FundsApi {
    private final FundService fundService;

    @Override
    public Fund createFund(CreateFundRequest r) {
        var f = com.example.investmentfunds.fund.model.Fund.builder()
                .withIsin(r.getIsin())
                .withName(r.getName())
                .withManagementCompany(r.getManagementCompany())
                .withCategory(r.getCategory())
                .withCurrency(r.getCurrency())
                .withInceptionDate(r.getInceptionDate())
                .withNavDate(r.getNavDate())
                .withNav(r.getNav())
                .withAssetsUnderManagement(r.getAssetsUnderManagement())
                .withInvestors(r.getInvestors())
                .withManagementFee(r.getManagementFee())
                .withDepositFee(r.getDepositFee())
                .withTer(r.getTer())
                .withReturnYtd(r.getReturnYtd())
                .withReturn1Year(r.getReturn1Year())
                .withReturn3Years(r.getReturn3Years())
                .withReturn5Years(r.getReturn5Years())
                .withRiskLevel(r.getRiskLevel())
                .withActive(r.getActive())
                .build();
        return map(fundService.create(f));
    }

    @Override
    public Fund getFundById(Long id) {
        return map(fundService.findById(id));
    }

    @Override
    public Fund patchFund(Long id, PatchFundRequest r) {
        var f = com.example.investmentfunds.fund.model.Fund.builder()
                .withIsin(r.getIsin())
                .withName(r.getName())
                .withManagementCompany(r.getManagementCompany())
                .withCategory(r.getCategory())
                .withCurrency(r.getCurrency())
                .withInceptionDate(r.getInceptionDate())
                .withNavDate(r.getNavDate())
                .withNav(r.getNav())
                .withAssetsUnderManagement(r.getAssetsUnderManagement())
                .withInvestors(r.getInvestors())
                .withManagementFee(r.getManagementFee())
                .withDepositFee(r.getDepositFee())
                .withTer(r.getTer())
                .withReturnYtd(r.getReturnYtd())
                .withReturn1Year(r.getReturn1Year())
                .withReturn3Years(r.getReturn3Years())
                .withReturn5Years(r.getReturn5Years())
                .withRiskLevel(r.getRiskLevel())
                .withActive(r.getActive())
                .build();
        return map(fundService.patch(id, f));
    }

    @Override
    public PageFund searchFunds(
            String isin,
            String name,
            String managementCompany,
            String category,
            String currency,
            Integer riskLevel,
            Boolean active,
            Integer page,
            Integer size) {
        var pageable = PageRequest.of(page == null ? 0 : page, size == null ? 20 : size, Sort.by("id"));
        var result = fundService.search(
                new FundSearchFilter(isin, name, managementCompany, category, currency, riskLevel, active), pageable);
        var response = new PageFund();
        response.setContent(result.map(this::map).getContent());
        response.setPage(result.getNumber());
        response.setSize(result.getSize());
        response.setTotalElements(result.getTotalElements());
        response.setTotalPages(result.getTotalPages());
        return response;
    }

    @Override
    public void deleteFund(Long id) {
        fundService.delete(id);
    }

    private Fund map(com.example.investmentfunds.fund.model.Fund source) {
        var target = new Fund();
        target.setId(source.getId());
        target.setIsin(source.getIsin());
        target.setName(source.getName());
        target.setManagementCompany(source.getManagementCompany());
        target.setCategory(source.getCategory());
        target.setCurrency(source.getCurrency());
        target.setRiskLevel(source.getRiskLevel());
        target.setActive(source.getActive());
        target.setInceptionDate(source.getInceptionDate());
        target.setNavDate(source.getNavDate());
        target.setNav(source.getNav());
        target.setAssetsUnderManagement(source.getAssetsUnderManagement());
        target.setInvestors(source.getInvestors());
        target.setManagementFee(source.getManagementFee());
        target.setDepositFee(source.getDepositFee());
        target.setTer(source.getTer());
        target.setReturnYtd(source.getReturnYtd());
        target.setReturn1Year(source.getReturn1Year());
        target.setReturn3Years(source.getReturn3Years());
        target.setReturn5Years(source.getReturn5Years());
        target.setCreatedAt(source.getCreatedAt());
        target.setUpdatedAt(source.getUpdatedAt());
        return target;
    }
}
