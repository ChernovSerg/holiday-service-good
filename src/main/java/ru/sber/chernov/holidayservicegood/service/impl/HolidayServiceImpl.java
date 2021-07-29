package ru.sber.chernov.holidayservicegood.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.chernov.holidayservicegood.config.Constants;
import ru.sber.chernov.holidayservicegood.domain.Processing;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;
import ru.sber.chernov.holidayservicegood.model.ProcessingDto;
import ru.sber.chernov.holidayservicegood.service.HolidayService;
import ru.sber.chernov.holidayservicegood.service.ProcessingService;
import ru.sber.chernov.holidayservicegood.service.UnstableServiceClient;
import ru.sber.chernov.holidayservicegood.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayServiceImpl implements HolidayService {
    private final UnstableServiceClient unstableServiceClient;
    private final ProcessingService processingService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public ProcessingDto getHolidayFromUnstable(IsHolidayRequest request) {
        ProcessingDto processing = ProcessingDto.builder().build();
        try {
            //вычисляем рабочий день, к-й стоит от заданного на N дней вперед или назад
            LocalDate date = LocalDate.parse(request.getDate(), FORMATTER);
            int numDays = request.getWorkDaysCount();
            int shift = numDays > 0 ? 1 : -1;
            int cntStepsWorkDay = 0;
            processing.setDateBegin(LocalDate.parse(request.getDate(), FORMATTER));
            processing.setCountWorkDay(numDays);
            processing.setCreated(LocalDateTime.now());
            while (cntStepsWorkDay != numDays) {
                date = date.plusDays(shift);
                String res = unstableServiceClient.isHoliday(date.format(FORMATTER));
                if (Integer.parseInt(res) == 0) {
                    cntStepsWorkDay += shift;
                }
            }
            processing.setDateResult(date);
            processing.setStatus(Constants.STATUS_OK);
            processing.setUpdated(LocalDateTime.now());
            log.info("Response from unstable service is: {}", date.format(FORMATTER));
        } catch (FeignException | DateTimeParseException exception) {
            log.error(exception.getMessage());
            processing.setStatus(Constants.STATUS_ERROR);
            processing.setUpdated(LocalDateTime.now());

            //попытка найти положительный ответ из кеша (БД) и вернуть его
            Processing processingCash = processingService.findSameRequest(LocalDate.parse(request.getDate(), FORMATTER), request.getWorkDaysCount());
            if (processingCash != null && processingCash.getDateResult() != null) {
                return Util.processingEntityToDto(processingCash);
            }
        }
        processingService.saveOrUpdateProcessing(processing);
        return processing;
    }
}
