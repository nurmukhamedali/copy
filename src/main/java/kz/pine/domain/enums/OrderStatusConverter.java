package kz.pine.domain.enums;


import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

public class OrderStatusConverter implements Converter<String, OrderStatus> {
    @Override
    public OrderStatus convert(String value) {
        return OrderStatus.valueOf(value.toUpperCase());
    }
}
