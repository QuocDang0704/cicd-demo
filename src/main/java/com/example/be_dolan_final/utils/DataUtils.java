package com.example.be_dolan_final.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Objects;

@UtilityClass
public class DataUtils {

    public static boolean ObjectIsNull(Object object) {
        return Objects.isNull(object);
    }

    public static boolean ObjectIsNonNull(Object object) {
        return Objects.nonNull(object);
    }

    public static boolean ObjectIsEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }

}
