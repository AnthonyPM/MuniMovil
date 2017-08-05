package pe.gob.munihuacho.munimovil.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.gob.munihuacho.munimovil.R;
import pe.gob.munihuacho.munimovil.model.Expediente;
import pe.gob.munihuacho.munimovil.model.ExpedienteDetalle;

/**
 * Created by alexisholyoak on 3/08/2017.
 */

public class TramiteDocumentarioDetailAdapter extends RecyclerView.Adapter<TramiteDocumentarioDetailAdapter.AdaptadorViewHolder>{


    public class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView text1, text2, text3,text4,text5,text6,text7,text8;

        AdaptadorViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            cardView = (CardView) view.findViewById(R.id.TramitDocDetailCard);
            text1 = (TextView) view.findViewById(R.id.fechaTDd);
            text2 = (TextView) view.findViewById(R.id.operacionTDd);
            text3 = (TextView) view.findViewById(R.id.formaTDd);
            text4=  (TextView) view.findViewById(R.id.unidadDestinoTDd);
            text5 =(TextView) view.findViewById(R.id.unidadOrganicaTDd);
            text6=(TextView)view.findViewById(R.id.usuarioTDd);
            text7=(TextView)view.findViewById(R.id.usuarioDestinoTDd);
            text8=(TextView)view.findViewById(R.id.proveidoTDd);
        }

        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }   private ArrayList<ExpedienteDetalle> mExpediente;
    private Context mContext;

    public TramiteDocumentarioDetailAdapter(Context context, ArrayList<ExpedienteDetalle> expedienteArray) {
        mContext = context;
        mExpediente = expedienteArray;
    }
    @Override
    public TramiteDocumentarioDetailAdapter.AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tramite_documentario_detail, parent, false);
        return new TramiteDocumentarioDetailAdapter.AdaptadorViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TramiteDocumentarioDetailAdapter.AdaptadorViewHolder holder, int position) {
        ExpedienteDetalle exp = mExpediente.get(position);
        String fecha=exp.getFechaDate();
        String hora=exp.getHora();
        String local=exp.getLocal();
        String forma=exp.getForma();
        String operacion=exp.getOperacion();
        String proveido = exp.getProveido();
        String unidad_destino=exp.getUnidad_destino();
        String unidad_organica=exp.getUnidad_organica();
        String usuario=exp.getUsuario();
        String usuario_destino=exp.getUsuario_destino();
        holder.text1.setText(fecha +" "+hora);
        holder.text2.setText(operacion);
        holder.text3.setText(forma);
        holder.text4.setText(unidad_destino);
        holder.text5.setText(unidad_organica);
        holder.text6.setText(usuario);
        holder.text7.setText(usuario_destino);
        holder.text8.setText(proveido);
    }
    @Override
    public int getItemCount() {
        return mExpediente.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private TramiteDocumentarioDetailAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }
    public void setOnEntryClickListener(TramiteDocumentarioDetailAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

}
