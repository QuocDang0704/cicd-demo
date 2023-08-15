package com.example.be_dolan_final.utils;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.be_dolan_final.utils.ApiStringUtils.join;


public class ApiEnumUtils {
    public static <T extends Enum<T>> T getByName(String name, Class<T> enumClass, String errorMessage, String errorNullMessage) {
        if (StringUtils.isBlank(name)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errorNullMessage);
        }
        String upperName = name.toUpperCase();
        for (T c : enumClass.getEnumConstants()) {
            if (c.name().equals(upperName)) {
                return c;
            }
        }
        List<String> enumValues = Stream.of(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, errorMessage + String.join(", ", enumValues));
    }

    public static <T extends Enum<T>> T getByName(final @Nullable String name, Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        return getByName(name, enumValues);
    }

    public static <T extends Enum<T>> Optional<T> findByName(final @Nullable String name, Class<T> enumClass) {
        try {
            T[] enumValues = enumClass.getEnumConstants();
            return Optional.ofNullable(getByName(name, enumValues));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T extends Enum<T>> T getByName(final @Nullable String name, T... enums) {
        return getByName(name, Lists.newArrayList(enums));
    }

    public static <T extends Enum<T>> T getByName(final @Nullable String name, List<T> enums) {
        if (Strings.isBlank(name)) {
            return null;
        }
        final String enumName = name.toUpperCase().trim();
        return enums.stream()
                .filter(enum_ -> enum_.toString().equals(enumName))
                .findFirst()
                .orElseThrow(() -> {
                    List<String> requiredValues = enums.stream().map(Enum::toString).collect(Collectors.toList());
                    return new RuntimeException(String.format("Giá trị phải thuộc: [%s]", join(", ", requiredValues)));
                });
    }

    public static <T extends Enum<T>> List<T> getByNames(final @Nullable List<String> names, Class<T> enumClass) {
        List<String> enumNames = ApiCollectionUtils.emptyListIfNull(names).stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (enumNames.isEmpty()) {
            return Lists.newArrayList();
        }

        return enumNames.stream().map(enumName -> getByName(enumName, enumClass)).collect(Collectors.toList());
    }

    public static <T extends Enum<T>> Set<String> getEnumNameSet(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toSet());
    }

    public static <T extends Enum<T>> List<String> getEnumNameListOf(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).collect(Collectors.toList());
    }

    public static <T extends Enum<T>> List<T> enumListOrListOf(String value, Class<T> enumClass) {
        if (Strings.isBlank(value)) {
            return Arrays.stream(enumClass.getEnumConstants()).collect(Collectors.toList());
        }
        return ApiCollectionUtils.listOf(getByName(value, enumClass));
    }

    public static <T extends Enum<T>> List<String> enumValueListOrListOf(String value, Class<T> enumClass) {
        if (Strings.isBlank(value)) {
            return getEnumNameListOf(enumClass);
        }
        return ApiCollectionUtils.listOf(getByName(value, enumClass).name());
    }

    public static List<String> enumsToList(Enum... arr) {
        return Arrays.stream(arr).map(Enum::name).collect(Collectors.toList());
    }

    public static <T extends Enum> void checkEnum(final String message, final T value, T... values) {
        if (!Lists.newArrayList(values).contains(value)) {
            throw new RuntimeException(String.format(message, (Object) values));
        }
    }

}
