package ru.sber.chernov.holidayservicegood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.sber.chernov.holidayservicegood.model.IsHolidayRequest;

@SpringBootApplication
@EnableFeignClients
public class HolidayServiceGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidayServiceGoodApplication.class, args);
    }

}
