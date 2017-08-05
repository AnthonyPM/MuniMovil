package pe.gob.munihuacho.munimovil.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexisholyoak on 4/08/2017.
 */

public class Predio implements Parcelable{
    private String contrib;
    private String descrip;
    private String nombre;
    private double nvabon;
    private double nvdeuda;
    private double nvtasas;
    private double saldo;
    private int secid;
    private String tributo;
    public Predio(){}
    protected Predio(Parcel in) {
        contrib = in.readString();
        descrip = in.readString();
        nombre = in.readString();
        nvabon = in.readDouble();
        nvdeuda = in.readDouble();
        nvtasas = in.readDouble();
        saldo = in.readDouble();
        secid = in.readInt();
        tributo = in.readString();
    }

    public static final Creator<Predio> CREATOR = new Creator<Predio>() {
        @Override
        public Predio createFromParcel(Parcel in) {
            return new Predio(in);
        }

        @Override
        public Predio[] newArray(int size) {
            return new Predio[size];
        }
    };

    public String getContrib() {
        return contrib;
    }

    public void setContrib(String contrib) {
        this.contrib = contrib;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getNvabon() {
        return nvabon;
    }

    public void setNvabon(double nvabon) {
        this.nvabon = nvabon;
    }

    public double getNvdeuda() {
        return nvdeuda;
    }

    public void setNvdeuda(double nvdeuda) {
        this.nvdeuda = nvdeuda;
    }

    public double getNvtasas() {
        return nvtasas;
    }

    public void setNvtasas(double nvtasas) {
        this.nvtasas = nvtasas;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getSecid() {
        return secid;
    }

    public void setSecid(int secid) {
        this.secid = secid;
    }

    public String getTributo() {
        return tributo;
    }

    public void setTributo(String tributo) {
        this.tributo = tributo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contrib);
        dest.writeString(descrip);
        dest.writeString(nombre);
        dest.writeDouble(nvabon);
        dest.writeDouble(nvdeuda);
        dest.writeDouble(nvtasas);
        dest.writeDouble(saldo);
        dest.writeInt(secid);
        dest.writeString(tributo);
    }
}
