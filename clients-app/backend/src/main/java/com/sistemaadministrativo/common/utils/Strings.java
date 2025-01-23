package com.sistemaadministrativo.common.utils;

import java.util.Objects;

public class Strings {
    private Strings() {
    }
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }
    public static boolean isEmpty(CharSequence string) {
        return Objects.isNull(string) || isEmpty(string.toString());
    }
    public static boolean isNotEmpty(CharSequence string) {
        return !isEmpty(string);
    }
    public static String truncate(String str, int length) {
        if (isEmpty(str)) {
            return "";
        }
        if (length < 0) {
            return str;
        }
        return str.codePoints()
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
