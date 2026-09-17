package com.example.investmentfunds.fund.service;

import com.example.investmentfunds.fund.dto.FundSearchFilter;
import com.example.investmentfunds.fund.model.Fund;
import org.springframework.data.domain.*;

public interface FundService {
    Fund create(Fund fund);

    Fund findById(Long id);

    Page<Fund> search(FundSearchFilter filter, Pageable pageable);

    Fund patch(Long id, Fund fund);

    void delete(Long id);
}
