package com.sistemaadministrativo.model.dto.validacion;

public class Observacion {
    public boolean error;
    public Integer corln;
    public String descripcion;

    public Observacion() {
    }

    public Observacion(boolean error, Integer corln, String descripcion) {
        this.error = error;
        this.corln = corln;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Observacion{" +
                "error=" + error +
                ", corln=" + corln +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
