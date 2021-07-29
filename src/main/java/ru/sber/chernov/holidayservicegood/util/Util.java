package ru.sber.chernov.holidayservicegood.util;

import ru.sber.chernov.holidayservicegood.domain.Processing;
import ru.sber.chernov.holidayservicegood.model.ProcessingDto;

public class Util {
    public static ProcessingDto processingEntityToDto(Processing entity) {
        return ProcessingDto.builder()
                .dateBegin(entity.getDateBegin())
                .countWorkDay(entity.getCountWorkDay())
                .dateResult(entity.getDateResult())
                .status(entity.getStatus())
                .created(entity.getCreated())
                .updated(entity.getUpdated())
                .build();
    }

    public static Processing ProcessingDtoToEntity(ProcessingDto dto) {
        return Processing.builder()
                .dateBegin(dto.getDateBegin())
                .countWorkDay(dto.getCountWorkDay())
                .dateResult(dto.getDateResult())
                .status(dto.getStatus())
                .created(dto.getCreated())
                .updated(dto.getUpdated())
                .countWorkDay(dto.getCountWorkDay())
                .build();
    }

    public static void copyProcessingDtoToEntity(ProcessingDto source, Processing target) {
        target.setDateBegin(source.getDateBegin());
        target.setCountWorkDay(source.getCountWorkDay());
        target.setDateResult(source.getDateResult());
        target.setStatus(source.getStatus());
        target.setCreated(source.getCreated());
        target.setUpdated(source.getUpdated());
    }
}
