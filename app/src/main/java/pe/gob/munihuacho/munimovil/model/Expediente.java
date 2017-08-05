package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexisholyoak on 3/08/2017.
 */

public class Expediente implements Parcelable {
    private int depe_id;
    private String expe_asunto;
    private String expe_cargo;
    private String expe_depe_detalle;
    private String expe_fecha;
    private String expe_fecha_doc;
    private String expe_firma;
    private int expe_folios;
    private int expe_forma;
    private String expe_hora;
    private int expe_id;
    private int expe_numero_doc;
    private int expe_origen;
    private String expe_proyectado;
    private int expe_relacionado;
    private String expe_siglas_doc;
    private int expe_tipo;
    private int frec_id;
    private int id_usu;
    private int tpri_id;
    private String texp_abreviado;
    private int texp_correlativo;
    private String texp_descripcion;
    private int texp_id;
    public Expediente(){

    }
    protected Expediente(Parcel in) {
        setDepe_id(in.readInt());
        setExpe_asunto(in.readString());
        setExpe_cargo(in.readString());
        setExpe_depe_detalle(in.readString());
        setExpe_fecha(in.readString());
        setExpe_fecha_doc(in.readString());
        setExpe_firma(in.readString());
        setExpe_folios(in.readInt());
        setExpe_forma(in.readInt());
        setExpe_hora(in.readString());
        setExpe_id(in.readInt());
        setExpe_numero_doc(in.readInt());
        setExpe_origen(in.readInt());
        setExpe_proyectado(in.readString());
        setExpe_relacionado(in.readInt());
        setExpe_siglas_doc(in.readString());
        setExpe_tipo(in.readInt());
        setFrec_id(in.readInt());
        setId_usu(in.readInt());
        setTpri_id(in.readInt());
        setTexp_abreviado(in.readString());
        setTexp_correlativo(in.readInt());
        setTexp_descripcion(in.readString());
        setTexp_id(in.readInt());
    }

    public static final Creator<Expediente> CREATOR = new Creator<Expediente>() {
        @Override
        public Expediente createFromParcel(Parcel in) {
            return new Expediente(in);
        }

        @Override
        public Expediente[] newArray(int size) {
            return new Expediente[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getDepe_id());
        dest.writeString(getExpe_asunto());
        dest.writeString(getExpe_cargo());
        dest.writeString(getExpe_depe_detalle());
        dest.writeString(getExpe_fecha());
        dest.writeString(getExpe_fecha_doc());
        dest.writeString(getExpe_firma());
        dest.writeInt(getExpe_folios());
        dest.writeInt(getExpe_forma());
        dest.writeString(getExpe_hora());
        dest.writeInt(getExpe_id());
        dest.writeInt(getExpe_numero_doc());
        dest.writeInt(getExpe_origen());
        dest.writeString(getExpe_proyectado());
        dest.writeInt(getExpe_relacionado());
        dest.writeString(getExpe_siglas_doc());
        dest.writeInt(getExpe_tipo());
        dest.writeInt(getFrec_id());
        dest.writeInt(getId_usu());
        dest.writeInt(getTpri_id());
        dest.writeString(getTexp_abreviado());
        dest.writeInt(getTexp_correlativo());
        dest.writeString(getTexp_descripcion());
        dest.writeInt(getTexp_id());
    }

    public int getDepe_id() {
        return depe_id;
    }

    public void setDepe_id(int depe_id) {
        this.depe_id = depe_id;
    }

    public String getExpe_asunto() {
        return expe_asunto;
    }

    public void setExpe_asunto(String expe_asunto) {
        this.expe_asunto = expe_asunto;
    }

    public String getExpe_cargo() {
        return expe_cargo;
    }

    public void setExpe_cargo(String expe_cargo) {
        this.expe_cargo = expe_cargo;
    }

    public String getExpe_depe_detalle() {
        return expe_depe_detalle;
    }

    public void setExpe_depe_detalle(String expe_depe_detalle) {
        this.expe_depe_detalle = expe_depe_detalle;
    }

    public String getExpe_fecha() {
        return expe_fecha;
    }

    public void setExpe_fecha(String expe_fecha) {
        this.expe_fecha = expe_fecha;
    }

    public String getExpe_fecha_doc() {
        return expe_fecha_doc;
    }

    public void setExpe_fecha_doc(String expe_fecha_doc) {
        this.expe_fecha_doc = expe_fecha_doc;
    }

    public String getExpe_firma() {
        return expe_firma;
    }

    public void setExpe_firma(String expe_firma) {
        this.expe_firma = expe_firma;
    }

    public int getExpe_folios() {
        return expe_folios;
    }

    public void setExpe_folios(int expe_folios) {
        this.expe_folios = expe_folios;
    }

    public int getExpe_forma() {
        return expe_forma;
    }

    public void setExpe_forma(int expe_forma) {
        this.expe_forma = expe_forma;
    }

    public String getExpe_hora() {
        return expe_hora;
    }

    public void setExpe_hora(String expe_hora) {
        this.expe_hora = expe_hora;
    }

    public int getExpe_id() {
        return expe_id;
    }

    public void setExpe_id(int expe_id) {
        this.expe_id = expe_id;
    }

    public int getExpe_numero_doc() {
        return expe_numero_doc;
    }

    public void setExpe_numero_doc(int expe_numero_doc) {
        this.expe_numero_doc = expe_numero_doc;
    }

    public int getExpe_origen() {
        return expe_origen;
    }

    public void setExpe_origen(int expe_origen) {
        this.expe_origen = expe_origen;
    }

    public String getExpe_proyectado() {
        return expe_proyectado;
    }

    public void setExpe_proyectado(String expe_proyectado) {
        this.expe_proyectado = expe_proyectado;
    }

    public int getExpe_relacionado() {
        return expe_relacionado;
    }

    public void setExpe_relacionado(int expe_relacionado) {
        this.expe_relacionado = expe_relacionado;
    }

    public String getExpe_siglas_doc() {
        return expe_siglas_doc;
    }

    public void setExpe_siglas_doc(String expe_siglas_doc) {
        this.expe_siglas_doc = expe_siglas_doc;
    }

    public int getExpe_tipo() {
        return expe_tipo;
    }

    public void setExpe_tipo(int expe_tipo) {
        this.expe_tipo = expe_tipo;
    }

    public int getFrec_id() {
        return frec_id;
    }

    public void setFrec_id(int frec_id) {
        this.frec_id = frec_id;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public int getTpri_id() {
        return tpri_id;
    }

    public void setTpri_id(int tpri_id) {
        this.tpri_id = tpri_id;
    }

    public String getTexp_abreviado() {
        return texp_abreviado;
    }

    public void setTexp_abreviado(String texp_abreviado) {
        this.texp_abreviado = texp_abreviado;
    }

    public int getTexp_correlativo() {
        return texp_correlativo;
    }

    public void setTexp_correlativo(int texp_correlativo) {
        this.texp_correlativo = texp_correlativo;
    }

    public String getTexp_descripcion() {
        return texp_descripcion;
    }

    public void setTexp_descripcion(String texp_descripcion) {
        this.texp_descripcion = texp_descripcion;
    }

    public int getTexp_id() {
        return texp_id;
    }

    public void setTexp_id(int texp_id) {
        this.texp_id = texp_id;
    }
}
