package com.example.investmentfunds.fund.mapper;

import com.example.investmentfunds.fund.dto.*;
import com.example.investmentfunds.fund.model.Fund;

public final class FundMapper {
    private FundMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Fund fromCreateRequest(CreateFundRequest r) {
        return Fund.builder()
                .withIsin(r.isin())
                .withName(r.name())
                .withManagementCompany(r.managementCompany())
                .withCategory(r.category())
                .withCurrency(r.currency())
                .withInceptionDate(r.inceptionDate())
                .withNavDate(r.navDate())
                .withNav(r.nav())
                .withAssetsUnderManagement(r.assetsUnderManagement())
                .withInvestors(r.investors())
                .withManagementFee(r.managementFee())
                .withDepositFee(r.depositFee())
                .withTer(r.ter())
                .withRiskLevel(r.riskLevel())
                .withReturnYtd(r.returnYtd())
                .withReturn1Year(r.return1Year())
                .withReturn3Years(r.return3Years())
                .withReturn5Years(r.return5Years())
                .withActive(r.active())
                .build();
    }

    public static Fund fromPatchRequest(PatchFundRequest r) {
        return Fund.builder()
                .withIsin(r.isin())
                .withName(r.name())
                .withManagementCompany(r.managementCompany())
                .withCategory(r.category())
                .withCurrency(r.currency())
                .withInceptionDate(r.inceptionDate())
                .withNavDate(r.navDate())
                .withNav(r.nav())
                .withAssetsUnderManagement(r.assetsUnderManagement())
                .withInvestors(r.investors())
                .withManagementFee(r.managementFee())
                .withDepositFee(r.depositFee())
                .withTer(r.ter())
                .withRiskLevel(r.riskLevel())
                .withReturnYtd(r.returnYtd())
                .withReturn1Year(r.return1Year())
                .withReturn3Years(r.return3Years())
                .withReturn5Years(r.return5Years())
                .withActive(r.active())
                .build();
    }

    public static void patch(PatchFundRequest r, Fund f) {
        if (r.isin() != null) f.setIsin(r.isin());
        if (r.name() != null) f.setName(r.name());
        if (r.managementCompany() != null) f.setManagementCompany(r.managementCompany());
        if (r.category() != null) f.setCategory(r.category());
        if (r.currency() != null) f.setCurrency(r.currency());
        if (r.inceptionDate() != null) f.setInceptionDate(r.inceptionDate());
        if (r.navDate() != null) f.setNavDate(r.navDate());
        if (r.nav() != null) f.setNav(r.nav());
        if (r.assetsUnderManagement() != null) f.setAssetsUnderManagement(r.assetsUnderManagement());
        if (r.investors() != null) f.setInvestors(r.investors());
        if (r.managementFee() != null) f.setManagementFee(r.managementFee());
        if (r.depositFee() != null) f.setDepositFee(r.depositFee());
        if (r.ter() != null) f.setTer(r.ter());
        if (r.riskLevel() != null) f.setRiskLevel(r.riskLevel());
        if (r.returnYtd() != null) f.setReturnYtd(r.returnYtd());
        if (r.return1Year() != null) f.setReturn1Year(r.return1Year());
        if (r.return3Years() != null) f.setReturn3Years(r.return3Years());
        if (r.return5Years() != null) f.setReturn5Years(r.return5Years());
        if (r.active() != null) f.setActive(r.active());
    }

    public static void merge(Fund p, Fund f) {
        if (p.getIsin() != null) f.setIsin(p.getIsin());
        if (p.getName() != null) f.setName(p.getName());
        if (p.getManagementCompany() != null) f.setManagementCompany(p.getManagementCompany());
        if (p.getCategory() != null) f.setCategory(p.getCategory());
        if (p.getCurrency() != null) f.setCurrency(p.getCurrency());
        if (p.getInceptionDate() != null) f.setInceptionDate(p.getInceptionDate());
        if (p.getNavDate() != null) f.setNavDate(p.getNavDate());
        if (p.getNav() != null) f.setNav(p.getNav());
        if (p.getAssetsUnderManagement() != null) f.setAssetsUnderManagement(p.getAssetsUnderManagement());
        if (p.getInvestors() != null) f.setInvestors(p.getInvestors());
        if (p.getManagementFee() != null) f.setManagementFee(p.getManagementFee());
        if (p.getDepositFee() != null) f.setDepositFee(p.getDepositFee());
        if (p.getTer() != null) f.setTer(p.getTer());
        if (p.getRiskLevel() != null) f.setRiskLevel(p.getRiskLevel());
        if (p.getReturnYtd() != null) f.setReturnYtd(p.getReturnYtd());
        if (p.getReturn1Year() != null) f.setReturn1Year(p.getReturn1Year());
        if (p.getReturn3Years() != null) f.setReturn3Years(p.getReturn3Years());
        if (p.getReturn5Years() != null) f.setReturn5Years(p.getReturn5Years());
        if (p.getActive() != null) f.setActive(p.getActive());
    }

    public static FundResponse toResponse(Fund f) {
        return new FundResponse(
                f.getId(),
                f.getIsin(),
                f.getName(),
                f.getManagementCompany(),
                f.getCategory(),
                f.getCurrency(),
                f.getInceptionDate(),
                f.getNavDate(),
                f.getNav(),
                f.getAssetsUnderManagement(),
                f.getInvestors(),
                f.getManagementFee(),
                f.getDepositFee(),
                f.getTer(),
                f.getRiskLevel(),
                f.getReturnYtd(),
                f.getReturn1Year(),
                f.getReturn3Years(),
                f.getReturn5Years(),
                f.getActive(),
                f.getCreatedAt(),
                f.getUpdatedAt());
    }
}
