package com.example.investmentfunds.fund.repository;

import com.example.investmentfunds.fund.dto.FundSearchFilter;
import com.example.investmentfunds.fund.entity.FundEntity;
import org.springframework.data.jpa.domain.Specification;

public final class FundSpecifications {
    private FundSpecifications() {
        throw new UnsupportedOperationException("This class should never be instantiated");
    }

    public static Specification<FundEntity> from(FundSearchFilter filter) {
        return (root, query, builder) -> {
            var predicate = builder.conjunction();
            if (filter.isin() != null)
                predicate = builder.and(predicate, builder.equal(root.get("isin"), filter.isin()));
            if (filter.name() != null)
                predicate = builder.and(predicate, builder.equal(root.get("name"), filter.name()));
            if (filter.managementCompany() != null)
                predicate = builder.and(
                        predicate, builder.equal(root.get("managementCompany"), filter.managementCompany()));
            if (filter.category() != null)
                predicate = builder.and(predicate, builder.equal(root.get("category"), filter.category()));
            if (filter.currency() != null)
                predicate = builder.and(predicate, builder.equal(root.get("currency"), filter.currency()));
            if (filter.riskLevel() != null)
                predicate = builder.and(predicate, builder.equal(root.get("riskLevel"), filter.riskLevel()));
            if (filter.active() != null)
                predicate = builder.and(predicate, builder.equal(root.get("active"), filter.active()));
            return predicate;
        };
    }
}
