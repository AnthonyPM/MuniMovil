package pe.gob.munihuacho.munimovil.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.PapeletasAdapter;
import pe.gob.munihuacho.munimovil.model.Papeleta;

public class PapeletasActivity extends AppCompatActivity {
    ArrayList<Papeleta> papeletas;
    RecyclerView RvPapeletas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papeletas);
        setTitle("Resultado de Papeletas");
        papeletas=getIntent().getParcelableArrayListExtra("listapapeletas");
        RvPapeletas=(RecyclerView)findViewById(R.id.RvPapeletas);
        RvPapeletas.setLayoutManager(new LinearLayoutManager(this));
        if(papeletas.size()==0){
            return;
        }
        PapeletasAdapter adapter=new PapeletasAdapter(this,papeletas);
        RvPapeletas.setAdapter(adapter);
        adapter.setOnEntryClickListener(new PapeletasAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Intent intent=new Intent(PapeletasActivity.this, PapeletaDetailActivity.class);
                intent.putExtra(PapeletaDetailActivity.DETALLE_PAPELETA,papeletas.get(position));
                startActivity(intent);
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
        builder.setMessage("El módulo de consulta de papeleta de tránsito permite mostrar el historial de papeletas asignadas a un determinado conductor registradas en la Municipalidad Provincial de Huaura.");
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
