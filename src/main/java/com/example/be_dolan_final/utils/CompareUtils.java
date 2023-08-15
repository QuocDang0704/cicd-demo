package com.example.be_dolan_final.utils;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.List;

import static com.example.be_dolan_final.utils.ApiEnumUtils.enumsToList;

@Slf4j
public class CompareUtils {

    public static boolean dateASC(Date date1, Date date2) {
        return date1.compareTo(date2) < 0;
    }

    public static boolean enumAndStr(Enum e, String value) {
        return e.name().equals(value);
    }

    public static boolean strMatchAny(String value, Enum... arr) {
        for (Enum e : arr) {
            if (e.name().equals(value)) return true;
        }
        return false;
    }

    public static List<String> anyMatchToList(String value, Enum... arr) {
        boolean isMatch = false;
        for (Enum e : arr) {
            if (e.name().equals(value)) {
                isMatch = true;
                break;
            }
        }
        return isMatch ? enumsToList(arr) : List.of(value);
    }

    @Data
    @Builder
    public static class MatchAny {
        private String input;
        private List<String> output;
        private List<Enum[]> patterns;

        public MatchAny validPattern() {
            if (this.input == null) this.input = "";
            for (Enum[] e : patterns) {
                if (strMatchAny(this.input, e)) {
                    this.output = enumsToList(e);
                    break;
                }
            }
            if (output == null || output.isEmpty()) {
                this.output = List.of(this.input);
                return this;
            }
            return this;
        }
    }

    public static <V, E> boolean strMatchAny(V value, List<E> arr, boolean ignoreCase) {
        if (value == null) return false;
        for (E e : arr) {
            if ((e != null && e.toString().equals(value.toString()))
                    || (e != null && ignoreCase
                    && e.toString().toLowerCase().equals(value.toString().toLowerCase()))
            ) return true;
        }
        return false;
    }

    //so sanh String, Enum
    //Cac object type khac can custom ham toString
    public static <V, E> boolean anyMatchLists(List<V> list1, List<E> list2, boolean ignoreCase) {
        if (list1 == null || list2 == null) return false;
        for (V v : list1) {
            if (strMatchAny(v, list2, ignoreCase)) return true;
        }
        return false;
    }

}
