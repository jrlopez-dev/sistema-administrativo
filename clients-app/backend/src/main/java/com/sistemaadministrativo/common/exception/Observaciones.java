package com.sistemaadministrativo.common.exception;


import com.sistemaadministrativo.common.utils.ListUtil;
import com.sistemaadministrativo.model.dto.validacion.Observacion;
import jakarta.validation.ConstraintViolation;

import java.util.*;

public class Observaciones {
    private List<Observacion> observList;

    private Observaciones() {
    }

    private Observaciones(boolean error, String msj) {
        set(error, msj);
    }

    public static Observacion desde(ConstraintViolation<Object> cv) {
        Observacion o = null;
        if (!Objects.isNull(cv)) {
            o = new Observacion();
            o.error = true;
            o.corln = 1;
            o.descripcion = cv.getMessage();
        }
        return o;
    }

    public static List<Observacion> desde(Collection<ConstraintViolation <Object>> lst) {
        List<Observacion> observaciones = new ArrayList<>();
        if (ListUtil.isNotEmpty(lst)) {
            int corln = 0;
            for (ConstraintViolation <Object> cv: lst) {
                corln++;
                Observacion o = desde(cv);
                if (!Objects.isNull(o)) {
                    o.corln = corln;
                }
                Collections.addAll(observaciones, o);
            }
        }
        return observaciones;
    }

    public static Observaciones error(CharSequence msj, Object... args) {
        return new Observaciones(true, stringTemp(msj, args));
    }

    public static Observaciones notificacion(CharSequence msj, Object... args) {
        return new Observaciones(false, stringTemp(msj, args));
    }

    public static Observaciones nuevas() {
        return new Observaciones();
    }

    public Observaciones observacion(boolean error, CharSequence msj, Object... args) {
        set(error, stringTemp(msj, args));
        return this;
    }

    public Observaciones observaciones(List<Observacion> lst) {
        if (Objects.nonNull(lst)) {
            for (Observacion o : lst) {
                observacion(o);
            }
        }
        return this;
    }
    public Observaciones observacion(Observacion o) {
        set(o.error, o.descripcion);
        return this;
    }

    private void set(boolean error, String msj) {
        if (Objects.isNull(observList)) {
            observList = new ArrayList<>();
        }
        observList.add(new Observacion(error, observList.size() + 1, msj));
    }

    private static String stringTemp(CharSequence tmpl, Object... args) {
        String str = "";
        if (Objects.nonNull(tmpl)) {
            if (ListUtil.isNotEmpty(args)) {
                str = String.format(String.valueOf(tmpl), args);
            } else {
                str = String.valueOf(tmpl);
            }
        }
        return str;
    }

    public List<Observacion> get() {
        return observList;
    }
}
