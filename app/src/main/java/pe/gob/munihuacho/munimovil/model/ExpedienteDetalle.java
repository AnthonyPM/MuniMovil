package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexisholyoak on 3/08/2017.
 */

public class ExpedienteDetalle implements Parcelable{
    private String fecha;
    private String fechaDate;
    private String forma;
    private String hora;
    private String local;
    private String operacion;
    private String proveido;
    private String unidad_destino;
    private String unidad_organica;
    private String usuario;
    private String usuario_destino;

    protected ExpedienteDetalle(Parcel in) {
        setFecha(in.readString());
        setFechaDate(in.readString());
        setForma(in.readString());
        setHora(in.readString());
        setLocal(in.readString());
        setOperacion(in.readString());
        setProveido(in.readString());
        setUnidad_destino(in.readString());
        setUnidad_organica(in.readString());
        setUsuario(in.readString());
        setUsuario_destino(in.readString());
    }
    public ExpedienteDetalle(){

    }

    public static final Creator<ExpedienteDetalle> CREATOR = new Creator<ExpedienteDetalle>() {
        @Override
        public ExpedienteDetalle createFromParcel(Parcel in) {
            return new ExpedienteDetalle(in);
        }

        @Override
        public ExpedienteDetalle[] newArray(int size) {
            return new ExpedienteDetalle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getFecha());
        dest.writeString(getFechaDate());
        dest.writeString(getForma());
        dest.writeString(getHora());
        dest.writeString(getLocal());
        dest.writeString(getOperacion());
        dest.writeString(getProveido());
        dest.writeString(getUnidad_destino());
        dest.writeString(getUnidad_organica());
        dest.writeString(getUsuario());
        dest.writeString(getUsuario_destino());
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(String fechaDate) {
        this.fechaDate = fechaDate;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getProveido() {
        return proveido;
    }

    public void setProveido(String proveido) {
        this.proveido = proveido;
    }

    public String getUnidad_destino() {
        return unidad_destino;
    }

    public void setUnidad_destino(String unidad_destino) {
        this.unidad_destino = unidad_destino;
    }

    public String getUnidad_organica() {
        return unidad_organica;
    }

    public void setUnidad_organica(String unidad_organica) {
        this.unidad_organica = unidad_organica;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuario_destino() {
        return usuario_destino;
    }

    public void setUsuario_destino(String usuario_destino) {
        this.usuario_destino = usuario_destino;
    }
}
