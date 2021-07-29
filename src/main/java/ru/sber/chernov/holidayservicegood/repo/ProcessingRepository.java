package ru.sber.chernov.holidayservicegood.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sber.chernov.holidayservicegood.domain.Processing;

import java.time.LocalDate;

@Repository
public interface ProcessingRepository extends JpaRepository<Processing, Integer> {
    Processing findByDateBeginAndCountWorkDay(LocalDate dateBegin, int countWorkDay);
}
