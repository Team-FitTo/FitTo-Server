package com.example.fittoserver.global.common.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    // 엔티티 -> DB (Duration을 초 단위 Long으로 변환)
    @Override
    public Long convertToDatabaseColumn(Duration attribute) {
        if (attribute == null) {
            return 0L;
        }
        return attribute.getSeconds();
    }

    // DB -> 엔티티 (Long 초 단위를 Duration으로 변환)
    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        if (dbData == null) {
            return Duration.ZERO;
        }
        return Duration.ofSeconds(dbData);
    }
}