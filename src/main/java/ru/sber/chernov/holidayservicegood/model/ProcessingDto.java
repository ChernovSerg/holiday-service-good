package ru.sber.chernov.holidayservicegood.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ProcessingDto {
    LocalDate dateBegin;
    int countWorkDay;
    LocalDate dateResult;
    LocalDateTime created;
    LocalDateTime updated;
    int status;
}
