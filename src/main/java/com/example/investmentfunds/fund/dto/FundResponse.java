package com.example.investmentfunds.fund.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public record FundResponse(
        Long id,
        String isin,
        String name,
        String managementCompany,
        String category,
        String currency,
        LocalDate inceptionDate,
        LocalDate navDate,
        BigDecimal nav,
        BigDecimal assetsUnderManagement,
        Integer investors,
        BigDecimal managementFee,
        BigDecimal depositFee,
        BigDecimal ter,
        Integer riskLevel,
        BigDecimal returnYtd,
        BigDecimal return1Year,
        BigDecimal return3Years,
        BigDecimal return5Years,
        Boolean active,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {}
