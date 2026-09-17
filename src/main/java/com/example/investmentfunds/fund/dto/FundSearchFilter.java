package com.example.investmentfunds.fund.dto;

public record FundSearchFilter(
        String isin,
        String name,
        String managementCompany,
        String category,
        String currency,
        Integer riskLevel,
        Boolean active) {}
