package com.example.investmentfunds.fund.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record PatchFundRequest(
        @Size(max = 12) String isin,
        @Size(max = 200) String name,
        @Size(max = 200) String managementCompany,
        @Size(max = 100) String category,
        @Size(min = 3, max = 3) String currency,
        LocalDate inceptionDate,
        LocalDate navDate,
        BigDecimal nav,
        BigDecimal assetsUnderManagement,
        Integer investors,
        BigDecimal managementFee,
        BigDecimal depositFee,
        BigDecimal ter,
        @Min(1) @Max(7) Integer riskLevel,
        BigDecimal returnYtd,
        BigDecimal return1Year,
        BigDecimal return3Years,
        BigDecimal return5Years,
        Boolean active) {}
