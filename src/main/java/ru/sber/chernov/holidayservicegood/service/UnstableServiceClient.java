package ru.sber.chernov.holidayservicegood.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.sber.chernov.holidayservicegood.config.FeignClientConfig;

@FeignClient(name = "unstableApiCalls", url = "${http_unstable_url}", configuration = FeignClientConfig.class)
public interface UnstableServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/isHoliday/{date}")
    String isHoliday(@PathVariable String date);
}
