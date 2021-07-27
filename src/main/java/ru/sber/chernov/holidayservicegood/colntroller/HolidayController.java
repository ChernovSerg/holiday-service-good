package ru.sber.chernov.holidayservicegood.colntroller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;
import ru.sber.chernov.holidayservicegood.service.HolidayService;

@RestController
@RequestMapping("/goodHoliday")
@RequiredArgsConstructor
@Slf4j
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/nextWorkDay")
    @Operation(summary = "Следующий рабочий день", description = "Вычисляет следующий рабочий день через N дней")
    public ResponseEntity<String> nextWorkDay(@RequestBody IsHolidayRequest request) {
        log.info("Trying to find out next work day {}", request);

        //бизнес логика формирования ответа для клиента в зависимости от ответа сервиса
        String responseService = holidayService.getHolidayFromUnstable(request);
        if (!NumberUtils.isDigits(responseService)) {
            return new ResponseEntity<>(responseService, HttpStatus.OK);
        } else {
            if (Integer.parseInt(responseService) == 0) {
                return new ResponseEntity<>("Accepted. Operation in progress.", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("External service for determine the holiday is not available", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
