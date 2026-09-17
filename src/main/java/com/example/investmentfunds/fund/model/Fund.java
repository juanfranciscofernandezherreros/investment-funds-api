package com.example.investmentfunds.fund.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Fund {
    private Long id;
    private String isin;
    private String name;
    private String managementCompany;
    private String category;
    private String currency;
    private LocalDate inceptionDate;
    private LocalDate navDate;
    private BigDecimal nav;
    private BigDecimal assetsUnderManagement;
    private Integer investors;
    private BigDecimal managementFee;
    private BigDecimal depositFee;
    private BigDecimal ter;
    private Integer riskLevel;
    private BigDecimal returnYtd;
    private BigDecimal return1Year;
    private BigDecimal return3Years;
    private BigDecimal return5Years;
    private Boolean active;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
