package ru.sber.chernov.holidayservicegood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IsHolidayRequest {

    @JsonProperty("date")
    private String date;

    @JsonProperty("workDaysCount")
    private int workDaysCount;
}