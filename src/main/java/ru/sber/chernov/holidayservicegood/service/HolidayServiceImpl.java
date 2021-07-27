package ru.sber.chernov.holidayservicegood.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


@Service
@RequiredArgsConstructor
@Slf4j
public class HolidayServiceImpl implements HolidayService {
    private final UnstableServiceClient unstableServiceClient;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
    public String getHolidayFromUnstable(IsHolidayRequest request) {
        String unstableResponse;
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
            log.info("Response from unstable service is: {}", unstableResponse);
            return unstableResponse;
        } catch (FeignException | DateTimeParseException exception) {
            log.error(exception.getMessage());
            return  "-1";
        }
    }
}
