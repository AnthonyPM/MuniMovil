package pe.gob.munihuacho.munimovil.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.activities.PrediosArbitriosActivity;
import pe.gob.munihuacho.munimovil.activities.TramiteDocumentarioActivity;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Predio;
import pe.gob.munihuacho.munimovil.util.Caracteres;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;

public class PrediosArbitriosFragment extends Fragment {
    String contrib;
    TextView numcontribPA;
    Button btnPrediosPA;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    static final String NAMESPACE="http://Ws/";
    static final String METHODNAME="buscarDeudaXPredio";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDePredioWS/ConsultarDeuda?WSDL";

    public PrediosArbitriosFragment() {
        // Required empty public constructor
    }

    public static PrediosArbitriosFragment newInstance(String param1, String param2) {
        PrediosArbitriosFragment fragment = new PrediosArbitriosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_predios_arbitrios, container, false);
        numcontribPA =(TextView)view.findViewById(R.id.numcontribPA);
        btnPrediosPA=(Button)view.findViewById(R.id.btnBuscarPA);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnPrediosPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Caracteres cambiar=new Caracteres();
                if(!numcontribPA.getText().toString().trim().equals("")){
                    contrib=cambiar.cofigurarExpediente(numcontribPA.getText().toString().trim());
                    new SoapAction(getActivity()).execute(contrib);
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un dato de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String,String,String>{
        Predio pred;
        ArrayList<Predio> predioArrayList=new ArrayList<Predio>();
        String notFoundMessage="";
        String notEthernetConnection="";
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("contrib",params[0]);
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
                            pred=new Predio();
                            pred.setNombre(obj2.getProperty("nombre").toString());
                            pred.setContrib(obj2.getProperty("contrib").toString());
                            pred.setDescrip(obj2.getProperty("descrip").toString());
                            pred.setNvabon(Double.parseDouble(obj2.getProperty("nvabon").toString()));
                            pred.setNvdeuda(Double.parseDouble(obj2.getProperty("nvdeuda").toString()));
                            pred.setNvtasas(Double.parseDouble(obj2.getProperty("nvtasas").toString()));
                            pred.setSaldo(Double.parseDouble(obj2.getProperty("saldo").toString()));
                            pred.setContrib(obj2.getProperty("contrib").toString());
                            predioArrayList.add(pred);
                        }
                    }else{
                        notFoundMessage="NO SE ENCONTRO REGISTRO";
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else{
                notEthernetConnection="FALLO EN LA CONEXION A INTERNET";
            }
            return null;
        }
        @Override
        protected void onPostExecute(String children) {
            if (notEthernetConnection != "") {
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notEthernetConnection, Toast.LENGTH_SHORT).show();
            }
            if (!notFoundMessage.equals("")){
                super.onPostExecute(children);
                Toast.makeText(getActivity(), notFoundMessage, Toast.LENGTH_SHORT).show();
            }else{
                super.onPostExecute(children);
                Intent intent=new Intent(getActivity(), PrediosArbitriosActivity.class);
                intent.putExtra("predioarray",predioArrayList);
                intent.putExtra("contrib",contrib);
                startActivity(intent);
            }
        }
    }
}
