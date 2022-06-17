package com.hgebk.dokobackend.service;

import com.hgebk.dokobackend.entity.Earning;
import com.hgebk.dokobackend.exception.DuplicateEarningException;
import com.hgebk.dokobackend.exception.EarningNotFoundException;
import com.hgebk.dokobackend.repository.EarningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

class EarningServiceTest {
    @Mock
    private EarningRepository earningRepository;

    private EarningService underTest;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        underTest = new EarningService(earningRepository);
    }

    @Test
    void getAllEarnings_withResults() {
        List<Earning> earnings = List.of(
                new Earning("foo1", 3.5, "foo1"),
                new Earning("bar1", 7, "bar1")
        );
        given(earningRepository.findAll()).willReturn(earnings);

        List<Earning> actualEarnings = underTest.getAllEarnings();

        assertThat(actualEarnings).isNotNull().containsAll(earnings);
    }

    @Test
    void getAllEarnings_withoutResults() {
        List<Earning> earnings = List.of();
        given(earningRepository.findAll()).willReturn(earnings);

        List<Earning> actualEarnings = underTest.getAllEarnings();

        assertThat(actualEarnings).isNotNull().isEmpty();
    }

    @Test
    void getEarning_found() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.of(earning));

        Earning actualEarning = underTest.getEarning("foo1");

        assertThat(actualEarning).isNotNull().isEqualTo(earning);
    }

    @Test
    void getEarning_notFound() {
        given(earningRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.getEarning("foo1"));

        assertThat(thrown)
                .isInstanceOf(EarningNotFoundException.class)
                .hasMessage("Earning with description 'foo1' does not exist");
    }

    @Test
    void saveEarning_new() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.empty());

        assertThatCode(() -> underTest.saveEarning(earning)).doesNotThrowAnyException();
    }

    @Test
    void saveEarning_duplicate() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.of(earning));

        Throwable thrown = catchThrowable(() -> underTest.saveEarning(earning));

        assertThat(thrown)
                .isInstanceOf(DuplicateEarningException.class)
                .hasMessage("Earning with description foo1 already exists");
    }

    @Test
    void updateEarning_found() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.of(earning));

        assertThatCode(() -> underTest.updateEarning(earning)).doesNotThrowAnyException();
    }

    @Test
    void updateEarning_notFound() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.updateEarning(earning));

        assertThat(thrown)
                .isInstanceOf(EarningNotFoundException.class)
                .hasMessage("Earning with description 'foo1' does not exist");
    }

    @Test
    void deleteEarningByDescription_found() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.of(earning));

        assertThatCode(() -> underTest.deleteEarningByDescription("foo1")).doesNotThrowAnyException();
    }

    @Test
    void deleteEarningByDescription_notFound() {
        Earning earning = new Earning("foo1", 3.5, "foo1");
        given(earningRepository.findById("foo1")).willReturn(Optional.empty());

        Throwable thrown = catchThrowable(() -> underTest.deleteEarningByDescription("foo1"));

        assertThat(thrown)
                .isInstanceOf(EarningNotFoundException.class)
                .hasMessage("Earning with description 'foo1' does not exist");
    }

    @Test
    void getTotalEarnings_twoResults() {
        List<Earning> earnings = List.of(
                new Earning("foo1", 3.5, "foo1"),
                new Earning("bar1", 7, "bar1")
        );
        given(earningRepository.findAll()).willReturn(earnings);

        double actualTotalEarnings = underTest.getTotalEarnings();

        assertThat(actualTotalEarnings)
                .isNotNull()
                .isNotNegative()
                .isEqualTo(10.5);
    }

    @Test
    void getTotalEarnings_noResults() {
        List<Earning> earnings = List.of();
        given(earningRepository.findAll()).willReturn(earnings);

        double actualTotalEarnings = underTest.getTotalEarnings();

        assertThat(actualTotalEarnings)
                .isNotNull()
                .isZero();
    }
}