package ru.sber.chernov.holidayservicegood.colntroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;
import ru.sber.chernov.holidayservicegood.service.HolidayService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/goodHoliday")
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/nextWorkDay")
    public String nextWorkDay(@RequestBody IsHolidayRequest request) {
        log.info("Trying to find out next work day {}", request);
        return holidayService.getHolidayFromUnstable(request);
    }
}
