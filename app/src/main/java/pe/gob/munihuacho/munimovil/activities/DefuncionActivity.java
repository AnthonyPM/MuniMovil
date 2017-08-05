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
import pe.gob.munihuacho.munimovil.adapters.DefuncionAdapter;
import pe.gob.munihuacho.munimovil.model.Defuncion;
import pe.gob.munihuacho.munimovil.model.Nacimiento;

public class DefuncionActivity extends AppCompatActivity {
    ArrayList<Defuncion> difuntos;
    RecyclerView rvDefuncion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defuncion);
        difuntos=getIntent().getParcelableArrayListExtra("muertos");
        rvDefuncion=(RecyclerView)findViewById(R.id.RvDefuncion);
        setTitle("Resultado de Defunciones");
        rvDefuncion.setLayoutManager(new LinearLayoutManager(this));
        if(difuntos.size()==0){
            return;
        }
        DefuncionAdapter adapter=new DefuncionAdapter(this,difuntos);
        rvDefuncion.setAdapter(adapter);
        adapter.setOnEntryClickListener(new DefuncionAdapter.OnEntryClickListener() {
            @Override
            public void onEntryClick(View view, int position) {
                Intent intent=new Intent(DefuncionActivity.this,DefuncionDetailActivity.class);
                intent.putExtra(DefuncionDetailActivity.DETALLE_DEFUNCION,difuntos.get(position));
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
        builder.setMessage("El módulo de consulta de Acta de Registro Civil permite verificar si la Acta de nacimiento, de matrimonio o de defunción de una persona se encuentra registrada en la Municipalidad Provincial de Huaura.");
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
