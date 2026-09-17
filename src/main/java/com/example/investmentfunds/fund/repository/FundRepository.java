package com.example.investmentfunds.fund.repository;

import com.example.investmentfunds.fund.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FundRepository extends JpaRepository<FundEntity, Long>, JpaSpecificationExecutor<FundEntity> {
    boolean existsByIsin(String isin);
}
