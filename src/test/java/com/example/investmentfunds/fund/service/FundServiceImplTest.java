package com.example.investmentfunds.fund.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import com.example.investmentfunds.common.exception.AppException;
import com.example.investmentfunds.fund.entity.FundEntity;
import com.example.investmentfunds.fund.model.Fund;
import com.example.investmentfunds.fund.repository.FundRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

@ExtendWith(MockitoExtension.class)
class FundServiceImplTest {
    @Mock
    private FundRepository fundRepository;

    @InjectMocks
    private FundServiceImpl fundService;

    @Test
    void find_by_id_when_existing_ok() {
        // given
        var entity = new FundEntity();
        entity.setId(1L);
        entity.setIsin("ES0000000001");
        entity.setName("Fund");
        when(fundRepository.findById(1L)).thenReturn(Optional.of(entity));
        // when
        var result = fundService.findById(1L);
        // then
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void find_by_id_when_missing_ko() {
        // given
        when(fundRepository.findById(1L)).thenReturn(Optional.empty());
        // when then
        assertThatThrownBy(() -> fundService.findById(1L)).isInstanceOf(AppException.class);
    }

    @Test
    void create_when_isin_is_available_ok() {
        var fund = fund("ES0000000001");
        var entity = entity(1L, "ES0000000001");
        when(fundRepository.existsByIsin(fund.getIsin())).thenReturn(false);
        when(fundRepository.save(any(FundEntity.class))).thenReturn(entity);

        var result = fundService.create(fund);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(fund.getCreatedAt()).isNotNull();
    }

    @Test
    void create_when_isin_exists_ko() {
        when(fundRepository.existsByIsin("ES0000000001")).thenReturn(true);

        assertThatThrownBy(() -> fundService.create(fund("ES0000000001"))).isInstanceOf(AppException.class);
        verify(fundRepository, never()).save(any(FundEntity.class));
    }

    @Test
    void search_returns_page_ok() {
        var pageable = PageRequest.of(0, 20);
        when(fundRepository.findAll(any(org.springframework.data.jpa.domain.Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(List.of(entity(1L, "ES0000000001")), pageable, 1));

        var result = fundService.search(
                new com.example.investmentfunds.fund.dto.FundSearchFilter(null, null, null, null, null, null, null),
                pageable);

        assertThat(result).hasSize(1);
    }

    @Test
    void patch_updates_present_fields_ok() {
        var entity = entity(1L, "ES0000000001");
        entity.setName("Old");
        when(fundRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(fundRepository.save(entity)).thenReturn(entity);

        var result = fundService.patch(1L, Fund.builder().withName("New").build());

        assertThat(result.getName()).isEqualTo("New");
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void patch_when_new_isin_exists_ko() {
        when(fundRepository.findById(1L)).thenReturn(Optional.of(entity(1L, "ES0000000001")));
        when(fundRepository.existsByIsin("ES0000000002")).thenReturn(true);

        assertThatThrownBy(() -> fundService.patch(
                        1L, Fund.builder().withIsin("ES0000000002").build()))
                .isInstanceOf(AppException.class);
    }

    @Test
    void delete_when_existing_ok() {
        var entity = entity(1L, "ES0000000001");
        when(fundRepository.findById(1L)).thenReturn(Optional.of(entity));

        fundService.delete(1L);

        verify(fundRepository).delete(entity);
    }

    private Fund fund(String isin) {
        return Fund.builder()
                .withIsin(isin)
                .withName("Fund")
                .withCurrency("EUR")
                .withActive(true)
                .build();
    }

    private FundEntity entity(Long id, String isin) {
        var entity = new FundEntity();
        entity.setId(id);
        entity.setIsin(isin);
        entity.setName("Fund");
        entity.setCurrency("EUR");
        entity.setActive(true);
        return entity;
    }
}
