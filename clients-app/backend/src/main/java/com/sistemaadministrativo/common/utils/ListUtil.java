package com.sistemaadministrativo.common.utils;

import java.util.Collection;

public class ListUtil {
    private ListUtil() {
    }
    public static  boolean isEmpty(Collection<?> lst) {
        return lst == null || lst.isEmpty();
    }
    public  static  boolean isNotEmpty(Collection<?> lst) {
        return !isEmpty(lst);
    }

    public static  boolean isEmpty(Object... args) {
        return args == null || args.length == 0;
    }

    public static  boolean isNotEmpty(Object... args) {
        return !isEmpty(args);
    }
}
