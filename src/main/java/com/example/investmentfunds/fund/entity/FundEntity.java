package com.example.investmentfunds.fund.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investment_funds")
@Getter
@Setter
@NoArgsConstructor
public class FundEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String isin;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "management_company", length = 200)
    private String managementCompany;

    @Column(length = 100)
    private String category;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(name = "inception_date")
    private LocalDate inceptionDate;

    @Column(name = "nav_date")
    private LocalDate navDate;

    @Column(precision = 18, scale = 6)
    private BigDecimal nav;

    @Column(name = "assets_under_management", precision = 20, scale = 2)
    private BigDecimal assetsUnderManagement;

    private Integer investors;

    @Column(name = "management_fee", precision = 8, scale = 4)
    private BigDecimal managementFee;

    @Column(name = "deposit_fee", precision = 8, scale = 4)
    private BigDecimal depositFee;

    @Column(precision = 8, scale = 4)
    private BigDecimal ter;

    @Column(name = "risk_level")
    private Integer riskLevel;

    @Column(name = "return_ytd", precision = 10, scale = 4)
    private BigDecimal returnYtd;

    @Column(name = "return_1_year", precision = 10, scale = 4)
    private BigDecimal return1Year;

    @Column(name = "return_3_years", precision = 10, scale = 4)
    private BigDecimal return3Years;

    @Column(name = "return_5_years", precision = 10, scale = 4)
    private BigDecimal return5Years;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
