package com.sistemaadministrativo.model.dto.validacion;



import com.sistemaadministrativo.common.exception.Observaciones;
import com.sistemaadministrativo.common.exception.ServiceException;
import com.sistemaadministrativo.common.utils.ListUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

public class Validacion {
    private Validator validator;
    private  Validacion(Validator validator){
        this.validator = validator;
    }
    public static Validacion nueva(Validator validator) {
        return new Validacion(validator);
    }

    public void ok(Object modelo) {
        Set<ConstraintViolation<Object>> obsrvs = validator.validate(modelo);
        List<Observacion> observaciones = Observaciones.desde(obsrvs);
        if (ListUtil.isNotEmpty(observaciones)) {
            throw new ServiceException(observaciones.toString());
        }
    }
}
