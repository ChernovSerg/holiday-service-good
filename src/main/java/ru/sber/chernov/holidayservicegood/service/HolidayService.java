package ru.sber.chernov.holidayservicegood.service;

import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;

public interface HolidayService {
    String getHolidayFromUnstable(IsHolidayRequest request);
}
