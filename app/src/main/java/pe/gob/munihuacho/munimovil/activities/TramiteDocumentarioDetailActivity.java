package pe.gob.munihuacho.munimovil.activities;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.TramiteDocumentarioAdapter;
import pe.gob.munihuacho.munimovil.adapters.TramiteDocumentarioDetailAdapter;
import pe.gob.munihuacho.munimovil.model.ExpedienteDetalle;

public class TramiteDocumentarioDetailActivity extends AppCompatActivity {
    ArrayList<ExpedienteDetalle> expedienteDetalleArrayList;
    RecyclerView RvTDd;
    String num;
    TextView tvTDdTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tramite_documentario_detail);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Detalle del Documento");
        expedienteDetalleArrayList=getIntent().getParcelableArrayListExtra("TDDetailarray");
        if(expedienteDetalleArrayList.size()==0){
            return;
        }
        tvTDdTitle=(TextView)findViewById(R.id.tvTDdTitle);
        num=getIntent().getStringExtra("num");
        tvTDdTitle.setText("TRAMITE DEL DOCUMENTO "+num);
        RvTDd=(RecyclerView)findViewById(R.id.RvTDd);
        RvTDd.setLayoutManager(new LinearLayoutManager(this));
        TramiteDocumentarioDetailAdapter documentarioDetailAdapter=new TramiteDocumentarioDetailAdapter(this,expedienteDetalleArrayList);
        RvTDd.setAdapter(documentarioDetailAdapter);

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
}
