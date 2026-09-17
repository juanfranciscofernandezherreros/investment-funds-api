package com.example.investmentfunds.fund.mapper;

import com.example.investmentfunds.fund.entity.FundEntity;
import com.example.investmentfunds.fund.model.Fund;

public final class FundEntityMapper {
    private FundEntityMapper() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Fund toModel(FundEntity e) {
        if (e == null) return null;
        return Fund.builder()
                .withId(e.getId())
                .withIsin(e.getIsin())
                .withName(e.getName())
                .withManagementCompany(e.getManagementCompany())
                .withCategory(e.getCategory())
                .withCurrency(e.getCurrency())
                .withInceptionDate(e.getInceptionDate())
                .withNavDate(e.getNavDate())
                .withNav(e.getNav())
                .withAssetsUnderManagement(e.getAssetsUnderManagement())
                .withInvestors(e.getInvestors())
                .withManagementFee(e.getManagementFee())
                .withDepositFee(e.getDepositFee())
                .withTer(e.getTer())
                .withRiskLevel(e.getRiskLevel())
                .withReturnYtd(e.getReturnYtd())
                .withReturn1Year(e.getReturn1Year())
                .withReturn3Years(e.getReturn3Years())
                .withReturn5Years(e.getReturn5Years())
                .withActive(e.getActive())
                .withCreatedAt(e.getCreatedAt())
                .withUpdatedAt(e.getUpdatedAt())
                .build();
    }

    public static FundEntity toEntity(Fund f) {
        FundEntity e = new FundEntity();
        updateEntity(f, e);
        return e;
    }

    public static void updateEntity(Fund f, FundEntity e) {
        e.setIsin(f.getIsin());
        e.setName(f.getName());
        e.setManagementCompany(f.getManagementCompany());
        e.setCategory(f.getCategory());
        e.setCurrency(f.getCurrency());
        e.setInceptionDate(f.getInceptionDate());
        e.setNavDate(f.getNavDate());
        e.setNav(f.getNav());
        e.setAssetsUnderManagement(f.getAssetsUnderManagement());
        e.setInvestors(f.getInvestors());
        e.setManagementFee(f.getManagementFee());
        e.setDepositFee(f.getDepositFee());
        e.setTer(f.getTer());
        e.setRiskLevel(f.getRiskLevel());
        e.setReturnYtd(f.getReturnYtd());
        e.setReturn1Year(f.getReturn1Year());
        e.setReturn3Years(f.getReturn3Years());
        e.setReturn5Years(f.getReturn5Years());
        e.setActive(f.getActive());
        e.setCreatedAt(f.getCreatedAt());
        e.setUpdatedAt(f.getUpdatedAt());
    }
}
