package com.example.investmentfunds.fund.service;

import com.example.investmentfunds.common.exception.*;
import com.example.investmentfunds.fund.dto.FundSearchFilter;
import com.example.investmentfunds.fund.mapper.FundEntityMapper;
import com.example.investmentfunds.fund.mapper.FundMapper;
import com.example.investmentfunds.fund.model.Fund;
import com.example.investmentfunds.fund.repository.*;
import java.time.OffsetDateTime;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class FundServiceImpl implements FundService {
    private final FundRepository fundRepository;

    @Override
    public Fund create(Fund fund) {
        if (fundRepository.existsByIsin(fund.getIsin())) throw new AppException(AppError.ISIN_ALREADY_EXISTS);
        fund.setCreatedAt(OffsetDateTime.now());
        var saved = fundRepository.save(FundEntityMapper.toEntity(fund));
        return FundEntityMapper.toModel(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Fund findById(Long id) {
        return FundEntityMapper.toModel(findEntity(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Fund> search(FundSearchFilter filter, Pageable pageable) {
        log.info("[FUND] - ACTION: search: page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());
        return fundRepository.findAll(FundSpecifications.from(filter), pageable).map(FundEntityMapper::toModel);
    }

    @Override
    public Fund patch(Long id, Fund patch) {
        var entity = findEntity(id);
        var current = FundEntityMapper.toModel(entity);
        FundMapper.merge(patch, current);
        if (!current.getIsin().equals(entity.getIsin()) && fundRepository.existsByIsin(current.getIsin()))
            throw new AppException(AppError.ISIN_ALREADY_EXISTS);
        current.setUpdatedAt(OffsetDateTime.now());
        FundEntityMapper.updateEntity(current, entity);
        return FundEntityMapper.toModel(fundRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        fundRepository.delete(findEntity(id));
    }

    private com.example.investmentfunds.fund.entity.FundEntity findEntity(Long id) {
        return fundRepository.findById(id).orElseThrow(() -> new AppException(AppError.FUND_NOT_FOUND));
    }
}
