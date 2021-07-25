package ru.sber.chernov.holidayservicegood.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayService {
    private final UnstableServiceClient unstableServiceClient;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ResponseEntity<String> getHolidayFromUnstable(IsHolidayRequest request) {
        HttpStatus status;
        String unstableResponse = "";
        try {
            //вычиляем рабочий день, к-й стоит от заданного на N дней вперед или назад
            String strDate = request.getDate();
            LocalDate date = LocalDate.parse(strDate, FORMATTER);
            int numDays = request.getWorkDaysCount();
            int shift = numDays > 0 ? 1 : -1;
            int cntStepsWorkDay = 0;
            while (cntStepsWorkDay != numDays) {
                date = date.plusDays(shift);
                String res = unstableServiceClient.isHoliday(date.format(FORMATTER));
                if (Integer.parseInt(res) == 0) {
                    cntStepsWorkDay += shift;
                }
            }
            unstableResponse = date.format(FORMATTER);
            status = HttpStatus.OK;
            log.info("Response from unstable service is: {}", unstableResponse);
        } catch (FeignException | DateTimeParseException exception) {
            log.error(exception.getMessage());
            unstableResponse = "External service for determine the holiday is not available:\n" + exception.getMessage();
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(unstableResponse, status);
    }
}
