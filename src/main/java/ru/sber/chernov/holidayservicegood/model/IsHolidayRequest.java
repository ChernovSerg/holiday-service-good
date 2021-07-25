package ru.sber.chernov.holidayservicegood.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Дата и кол-во рабочих дней")
public class IsHolidayRequest {

    @JsonProperty("date")
    @Schema(description = "Дата в виде DD-MM-YYYY", example = "22-07-2021")
    private String date;

    @JsonProperty("workDaysCount")
    @Schema(description = "Кол-во рабочих дней (положительное или отрицательное), которое необходимо прибавить или отнять от Даты date", example = "5")
    private int workDaysCount;
}