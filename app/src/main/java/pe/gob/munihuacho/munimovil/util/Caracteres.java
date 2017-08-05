package pe.gob.munihuacho.munimovil.util;

/**
 * Created by peral on 29/03/2017.
 */

public class Caracteres {
    public String fechaSet(String id){
        String formatoFecha="";
        char[] arrayChar=id.toCharArray();

        if(arrayChar.length==2){
            formatoFecha=id;
            return formatoFecha;
        }
        if(arrayChar.length==1){
            formatoFecha="0"+id;
            return formatoFecha;
        }
        return formatoFecha;
    }
    public boolean movimientoSet(String movimiento){
        char[] arrayChar=movimiento.toCharArray();
        if(arrayChar.length>7){
            return true;
        }else{
            //Si la cadena de caracteres de movimiento no es igual a 7

            return false;
        }
    }
    public String modificarMovimientos(String movimiento){
        char[] arrayChar=movimiento.toCharArray();
        String resultado="";
        String ceros="";
        if (arrayChar.length<=7){
            int cantidadDeCeros=7-movimiento.length();
          for (int i=0;i<cantidadDeCeros;i++){
            ceros=ceros+"0";
          }
        }
        resultado=ceros+movimiento;
        return resultado;
    }
    public String modificarUsuario(String usuario){
        char[] arrayChar=usuario.toCharArray();
        String resultado="";
        String ceros="";
        if(arrayChar.length<=10){
            int cantidadDeCeros=10-usuario.length();
            for(int i=0;i<cantidadDeCeros;i++){
                ceros=ceros+"0";
            }
        }
        resultado=ceros+usuario;
        return resultado;
    }
    public String cofigurarExpediente(String exp){
        char[] arrayChar=exp.toCharArray();
        String resultado="";
        String ceros="";
        if(arrayChar.length<=10){
            int cantidadDeCeros=10-exp.length();
            for(int i=0;i<cantidadDeCeros;i++){
                ceros=ceros+"0";
            }
        }
        resultado=ceros+exp;
        return resultado;
    }
    public String configurarPlaca(String placa){
        return placa.toUpperCase();
    }
    public boolean validarFecha(String fecha){
        char[] arrayChar=fecha.toCharArray();
        for (int i=0;i<arrayChar.length;i++){
            if(!String.valueOf(arrayChar[5]).equals("-")){
                return false;
            }
            if(!String.valueOf(arrayChar[8]).equals("-")){
                return false;
            }
        }
        if(arrayChar.length>10){
            return false;
        }
        return  true;
    }
}
