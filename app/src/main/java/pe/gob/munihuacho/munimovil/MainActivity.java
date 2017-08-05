package pe.gob.munihuacho.munimovil;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import pe.gob.munihuacho.munimovil.activities.LoginActivity;
import pe.gob.munihuacho.munimovil.activities.SisgedoActivity;
import pe.gob.munihuacho.munimovil.fragments.AlertaHuachoFragment;
import pe.gob.munihuacho.munimovil.fragments.ConsultarPagoFragment;
import pe.gob.munihuacho.munimovil.fragments.ConsultarPapeletasFragment;
import pe.gob.munihuacho.munimovil.fragments.ContactanosFragment;
import pe.gob.munihuacho.munimovil.fragments.HomeFragment;
import pe.gob.munihuacho.munimovil.fragments.OperacionVehiculoFragment;
import pe.gob.munihuacho.munimovil.fragments.PrediosArbitriosFragment;
import pe.gob.munihuacho.munimovil.fragments.RegistroEstadoCivilFragment;
import pe.gob.munihuacho.munimovil.fragments.TramiteDocumentarioFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ImageView navtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Para el portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment inicio=HomeFragment.newInstance("Home","Inicio");
        openFragment(inicio);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        /**NAV HEADER*/
        /****/
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount() > 1) {
                getSupportFragmentManager().popBackStack();
            }else{
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment newFragment=null;

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id==R.id.action_home){
            newFragment= HomeFragment.newInstance("Inicio","Home");
            setTitle("Inicio");
            openFragment(newFragment);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        Fragment newFragment=null;
        if (id == R.id.nav_consultarpago) {
            // Handle the camera action
            newFragment= ConsultarPagoFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle("Consultar pago en");
        } else if (id == R.id.nav_operacionvehiculo) {
            newFragment= OperacionVehiculoFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        } else if (id == R.id.nav_regyestadocivil) {
            newFragment= RegistroEstadoCivilFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        } else if (id == R.id.nav_papeletas) {
            newFragment= ConsultarPapeletasFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        }else if(id==R.id.nav_predios_arbitrios){
            newFragment = PrediosArbitriosFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        }else if(id==R.id.nav_tramites_documentarios){
            newFragment = TramiteDocumentarioFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        }else if(id==R.id.nav_alerta_opcion){
            newFragment= AlertaHuachoFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        }
        else if(id==R.id.nav_contactanos){
            newFragment= ContactanosFragment.newInstance(String.valueOf(id),item.getTitle().toString());
            setTitle(item.getTitle());
        }
        if(newFragment!=null){

            openFragment(newFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void openFragment(Fragment newFragment){
        Fragment containerFragment = getSupportFragmentManager().findFragmentById(R.id.content_main);
        if (containerFragment == null){
            addFragment(newFragment);
        } else{
            if (!containerFragment.getClass().getName().equalsIgnoreCase(newFragment.getClass().getName())) {
                replaceFragment(newFragment);
            }
        }

    }
    private void replaceFragment(Fragment newFragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.content_main,newFragment);
        ft.addToBackStack(newFragment.getClass().getName());
        ft.commit();
    }
    private void addFragment(Fragment newFragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.content_main, newFragment);
        ft.commit();
    }
}
