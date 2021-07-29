package ru.sber.chernov.holidayservicegood.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sber.chernov.holidayservicegood.domain.Processing;
import ru.sber.chernov.holidayservicegood.model.ProcessingDto;
import ru.sber.chernov.holidayservicegood.repo.ProcessingRepository;
import ru.sber.chernov.holidayservicegood.util.Util;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProcessingService {

    private final ProcessingRepository processingRepository;

    public Processing findSameRequest(LocalDate date, int countWorkDay) {
        return processingRepository.findByDateBeginAndCountWorkDay(date, countWorkDay);
    }

    public void saveOrUpdateProcessing(ProcessingDto processingSource) {
        Processing processingDestination = processingRepository.findByDateBeginAndCountWorkDay(processingSource.getDateBegin(), processingSource.getCountWorkDay());
        if (processingDestination == null) {
            processingDestination = new Processing();
        }
        Util.copyProcessingDtoToEntity(processingSource,processingDestination);
        processingRepository.save(processingDestination);
    }
}
