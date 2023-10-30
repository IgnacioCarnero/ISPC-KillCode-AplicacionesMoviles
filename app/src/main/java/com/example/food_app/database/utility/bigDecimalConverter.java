package com.example.food_app.database.utility;

import androidx.room.TypeConverter;
import java.math.BigDecimal;
public class bigDecimalConverter {
    @TypeConverter
    public static BigDecimal fromString(String value) {
        return new BigDecimal(value);
    }

    @TypeConverter
    public static String toString(BigDecimal value) {
        return value.toString();
    }
}
