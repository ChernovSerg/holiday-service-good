package ru.sber.chernov.holidayservicegood.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;


@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayService {
    private final UnstableServiceClient unstableServiceClient;

    public String getHolidayFromUnstable(IsHolidayRequest request) {
        String unstableResponse = "";
        try {
            unstableResponse = unstableServiceClient.isHoliday(request.getDate());
            log.info("Response from unstable service is: {}", unstableResponse);
        } catch (FeignException exception) {
            log.error(exception.getMessage());
            unstableResponse = exception.getMessage();
        }

        return unstableResponse;
    }
}
