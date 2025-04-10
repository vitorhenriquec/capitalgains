package com.capitalgains.infrastructure.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormatConverter {

    public static BigDecimal toBigDecimal(double number) {
        return BigDecimal.valueOf(number)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
