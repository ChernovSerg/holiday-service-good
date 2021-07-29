package ru.sber.chernov.holidayservicegood.service;

import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;
import ru.sber.chernov.holidayservicegood.model.ProcessingDto;

public interface HolidayService {
    ProcessingDto getHolidayFromUnstable(IsHolidayRequest request);
}
