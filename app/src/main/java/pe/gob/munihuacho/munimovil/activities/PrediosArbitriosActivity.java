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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.adapters.PredioArbitrioAdapter;
import pe.gob.munihuacho.munimovil.model.Predio;

public class PrediosArbitriosActivity extends AppCompatActivity {
    String contrib;
    ArrayList<Predio> predioArrayList;
    TextView tvPAtitle;
    RecyclerView rvPA;
    String nombre;
    TextView tvNombrePA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predios_arbitrios);
        predioArrayList=getIntent().getParcelableArrayListExtra("predioarray");
        setTitle("Predios y Arbitrios");
        contrib=predioArrayList.get(0).getContrib();
        nombre=predioArrayList.get(0).getNombre();
        rvPA=(RecyclerView)findViewById(R.id.rvPA);
        tvPAtitle=(TextView)findViewById(R.id.tvPAtitle);
        tvNombrePA=(TextView)findViewById(R.id.tvNombrePA);
        tvPAtitle.setText(contrib);
        tvNombrePA.setText(nombre);
        rvPA.setLayoutManager(new LinearLayoutManager(this));
        if(predioArrayList.size()==0){
            return;
        }
        PredioArbitrioAdapter predioArbitrioAdapter=new PredioArbitrioAdapter(this,predioArrayList);
        rvPA.setAdapter(predioArbitrioAdapter);

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
        builder.setMessage("El módulo de consulta de Predios y Arbitrios permite mostrar la deuda que tiene contribuyente con la Municipalidad Provincial de Huaura.");
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
