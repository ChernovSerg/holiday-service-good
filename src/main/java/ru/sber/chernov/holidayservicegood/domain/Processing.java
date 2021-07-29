package ru.sber.chernov.holidayservicegood.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "processing")
public class Processing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "date_begin", nullable = false)
    LocalDate dateBegin;

    @Column(name = "count_work_day", nullable = false)
    int countWorkDay;

    @Column(name = "date_result")
    LocalDate dateResult;

    @Column(name = "created", nullable = false)
    LocalDateTime created;

    @Column(name = "updated")
    LocalDateTime updated;

    @Column(name = "status")
    int status;

}
