package pe.gob.munihuacho.munimovil.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.adapters.TramiteDocumentarioAdapter;
import pe.gob.munihuacho.munimovil.model.Expediente;
import pe.gob.munihuacho.munimovil.model.ExpedienteDetalle;
import pe.gob.munihuacho.munimovil.model.Papeleta;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;


public class TramiteDocumentarioActivity extends AppCompatActivity {
    String expe_id;
    ArrayList<Expediente> expedienteArrayList;
    RecyclerView RvTramiteDocumentario;
    TextView tvTDtitle;

    static final String NAMESPACE="http://Ws/";
    static final String METHODNAME="buscarTramiteDocumentarioDetalle";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeTramiteDocumentarioWS/TramiteDocumentario?WSDL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tramite Documentario");
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_tramite_documentario);
        expedienteArrayList=getIntent().getParcelableArrayListExtra("expedientearray");
        RvTramiteDocumentario=(RecyclerView)findViewById(R.id.RvTramiteDocumentario);
        RvTramiteDocumentario.setLayoutManager(new LinearLayoutManager(this));
        if(expedienteArrayList.size()==0){
            return;
        }
        //String num=String.valueOf(expedienteArrayList.get(0).getExpe_id());
        //tvTDtitle=(TextView)findViewById(R.id.tvTDdTitle);
        //tvTDtitle.setText("N° DE EXPEDIENTE "+num);
        TramiteDocumentarioAdapter documentarioAdapter=new TramiteDocumentarioAdapter(this,expedienteArrayList);
        RvTramiteDocumentario.setAdapter(documentarioAdapter);
        documentarioAdapter.setOnEntryClickListener(new TramiteDocumentarioAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                //HANDLE DETAILS FOR TRAMITE DOCUMENTARIO
                expe_id=String.valueOf(expedienteArrayList.get(position).getExpe_id());
                new SoapAction(TramiteDocumentarioActivity.this).execute(expe_id);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_info, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_back_i){
            if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
        if(id==R.id.action_info){
            infoDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    public void infoDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("INFORMACION");
        builder.setMessage("El módulo de consulta de Trámite Documentario permite mostrar información a detalle del flujo de tu expediente en la Municipalidad Provincial de Huaura.");
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private class SoapAction extends  AsynctaskWithDelayedIndeterminateProgress<String,String,String>{
        String notFoundMessage="";
        String notEthernetConnection="";
        ExpedienteDetalle expedienteDetalle;
        ArrayList<ExpedienteDetalle> expedienteDetalleArrayList=new ArrayList<ExpedienteDetalle>();
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(TramiteDocumentarioActivity.this).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("expe_id",params[0]);
                SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=false;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte=new HttpTransportSE(URL);
                try{
                    transporte.debug=true;
                    transporte.call(SOAP_ACTION,envelope);
                    SoapObject obj1=(SoapObject)envelope.bodyIn;
                    if (obj1.getPropertyCount()>0){
                        for (int i=0;i<obj1.getPropertyCount();i++){
                            SoapObject obj2=(SoapObject)obj1.getProperty(i);
                            expedienteDetalle=new ExpedienteDetalle();
                            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                            Date dateStr=formatter.parse(obj2.getProperty("fechaDate").toString());
                            String fech=formatter.format(dateStr);
                            expedienteDetalle.setFechaDate(fech);
                            expedienteDetalle.setForma(obj2.getProperty("forma").toString());
                            expedienteDetalle.setHora(obj2.getProperty("hora").toString());
                            expedienteDetalle.setOperacion(obj2.getProperty("operacion").toString());
                            String proveido="";
                            if(obj2.hasProperty("proveido")){
                                Object obj = obj2.getProperty("proveido");
                                if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                                    SoapPrimitive j =(SoapPrimitive) obj;
                                    proveido = j.toString();
                                    expedienteDetalle.setProveido(proveido);
                                }else if (obj!= null && obj instanceof String){
                                    proveido = (String) obj;
                                    expedienteDetalle.setProveido(proveido);
                                }
                            }else{
                                expedienteDetalle.setProveido("");
                            }
                            if (obj2.hasProperty("usuario_destino"))
                            {
                                expedienteDetalle.setUsuario_destino(obj2.getProperty("usuario_destino").toString());
                            }else{
                                expedienteDetalle.setUsuario_destino("");
                            }
                            if(obj2.hasProperty("unidad_destino")){
                                expedienteDetalle.setUnidad_destino(obj2.getProperty("unidad_destino").toString());
                            }else{
                                expedienteDetalle.setUnidad_destino("");
                            }
                            expedienteDetalle.setUnidad_organica(obj2.getProperty("unidad_organica").toString());
                            expedienteDetalle.setUsuario(obj2.getProperty("usuario").toString());
                            expedienteDetalleArrayList.add(expedienteDetalle);
                        }
                    }
            }catch (Exception ex){
                    ex.printStackTrace();
            }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            if (notEthernetConnection != "") {
                super.onPostExecute(children);
                Toast.makeText(TramiteDocumentarioActivity.this, notEthernetConnection, Toast.LENGTH_SHORT).show();
            }
            if (!notFoundMessage.equals("")){
                super.onPostExecute(children);
                Toast.makeText(TramiteDocumentarioActivity.this, notFoundMessage, Toast.LENGTH_SHORT).show();
            }else{
                super.onPostExecute(children);
                Intent intent=new Intent(TramiteDocumentarioActivity.this, TramiteDocumentarioDetailActivity.class);
                intent.putExtra("TDDetailarray",expedienteDetalleArrayList);
                intent.putExtra("num",expe_id);
                startActivity(intent);
            }
        }
    }
}
