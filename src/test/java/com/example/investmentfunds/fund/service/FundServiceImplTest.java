package com.example.investmentfunds.fund.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.example.investmentfunds.common.exception.AppException;
import com.example.investmentfunds.fund.entity.FundEntity;
import com.example.investmentfunds.fund.repository.FundRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
