package com.sistemaadministrativo.model.dto.validacion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidacionException extends RuntimeException{
    private List<Observacion> observaciones;

    public ValidacionException(CharSequence msj) {
        super(String.valueOf(msj));
    }

    public ValidacionException(List < Observacion > lst) {
        super(String.format("Existen advertencias que deben ser atendidas: %s. ",
                lst.stream().map(o -> o.descripcion).collect(Collectors.joining(", "))));
        observaciones = lst;
    }

    public ValidacionException(Observacion o) {
        super(String.format("Existen advertencias que deben ser atendidas: %s. ",
                Arrays.asList(o).stream().map(obsrv -> obsrv.descripcion).collect(Collectors.joining(", "))));
        observaciones = Arrays.asList(o);
    }


    public List<Observacion> getObservaciones() {
        return observaciones;
    }
}
