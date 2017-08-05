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
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.activities.PapeletasActivity;
import pe.gob.munihuacho.munimovil.activities.TramiteDocumentarioActivity;
import pe.gob.munihuacho.munimovil.adapters.AsynctaskWithDelayedIndeterminateProgress;
import pe.gob.munihuacho.munimovil.model.Expediente;
import pe.gob.munihuacho.munimovil.util.CheckNetwork;


public class TramiteDocumentarioFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static final String NAMESPACE="http://Ws/";
    static final String METHODNAME="buscarTramiteDocumentario";
    static final String SOAP_ACTION=NAMESPACE+METHODNAME;
    static final String URL="http://apps2.munihuacho.gob.pe:9090/ConsultaDeTramiteDocumentarioWS/TramiteDocumentario?WSDL";
    private String mParam1;
    private String mParam2;
    String exma_id;
    EditText expedienteTD;
    Button btnBuscarTD;
    public TramiteDocumentarioFragment() {
        // Required empty public constructor
    }

    public static TramiteDocumentarioFragment newInstance(String param1, String param2) {
        TramiteDocumentarioFragment fragment = new TramiteDocumentarioFragment();
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
        View view=inflater.inflate(R.layout.fragment_tramite_documentario, container, false);
        expedienteTD=(EditText)view.findViewById(R.id.numexpedienteTD);
        btnBuscarTD=(Button) view.findViewById(R.id.btnBuscarTD);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnBuscarTD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!expedienteTD.getText().toString().trim().equals("")){
                exma_id=expedienteTD.getText().toString().trim();
               new SoapAction(getActivity()).execute(exma_id);
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar un dato de busqueda", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class SoapAction extends AsynctaskWithDelayedIndeterminateProgress<String,String,String> {
        Expediente exp;
        ArrayList<Expediente> arrayExp=new ArrayList<Expediente>();
        String notFoundMessage="";
        String notEthernetConnection="";
        protected SoapAction(Activity activity) {
            super(activity);
        }
        @Override
        protected String doInBackground(String... params) {
            if (new CheckNetwork(getActivity()).isNetworkAvailable()){
                SoapObject request=new SoapObject(NAMESPACE,METHODNAME);
                request.addProperty("exma_id",params[0]);
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
                            exp=new Expediente();
                            //exp.setExpe_id(Integer.parseInt(obj2.getProperty("expe_id").toString()));
                            exp.setExpe_asunto(obj2.getProperty("expe_asunto").toString());
                            exp.setExpe_fecha(obj2.getProperty("expe_fecha").toString());
                            exp.setExpe_firma(obj2.getProperty("expe_firma").toString());
                            exp.setTexp_descripcion(obj2.getProperty("texp_descripcion").toString());
                            exp.setExpe_numero_doc(Integer.parseInt(obj2.getProperty("expe_numero_doc").toString()));
                            exp.setExpe_siglas_doc(obj2.getProperty("expe_siglas_doc").toString());
                            exp.setExpe_id(Integer.parseInt(obj2.getProperty("expe_id").toString()));
                            //exp.setTexp_correlativo(Integer.parseInt(obj2.getProperty("texp_correlativo").toString()));
                            arrayExp.add(exp);
                        }
                    }else{
                        notFoundMessage="No se encontro ningun registro";
                    }
                }catch (Exception ex){

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
                Intent intent=new Intent(getActivity(), TramiteDocumentarioActivity.class);
                intent.putExtra("expedientearray",arrayExp);
                startActivity(intent);
            }
        }
    }
}
