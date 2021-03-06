package ar.edu.unnoba.ppc.dfernandez.tp_final_ppc_unnoba;

import com.google.android.gms.maps.model.LatLng;

public class Obra {
    String descripcion;
    String detalle;
    Double distancia;
    String domicilio;
    LatLng location;
    Long telefono;
    static final String TIPO = "OBRA";
    Double valor;

    //<editor-fold desc="Getters y Setters">
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    //</editor-fold>


    @Override
    public String toString() {
        return "Obra{" +
                "descripcion='" + descripcion + '\'' +
                ", detalle='" + detalle + '\'' +
                ", distancia=" + distancia +
                ", domicilio='" + domicilio + '\'' +
                ", location=" + location +
                ", telefono=" + telefono +
                ", valor=" + valor +
                '}';
    }
}
