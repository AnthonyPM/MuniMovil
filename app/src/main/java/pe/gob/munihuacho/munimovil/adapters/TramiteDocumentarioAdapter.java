package pe.gob.munihuacho.munimovil.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Vector;
import java.util.ArrayList;
import pe.gob.munihuacho.munimovil.R;

import pe.gob.munihuacho.munimovil.model.Expediente;
import pe.gob.munihuacho.munimovil.model.Papeleta;

/**
 * Created by alexisholyoak on 3/08/2017.
 */

public class TramiteDocumentarioAdapter extends RecyclerView.Adapter<TramiteDocumentarioAdapter.AdaptadorViewHolder>{
public class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    CardView cardView;
    TextView text1, text2, text3,text4,text5;
    AdaptadorViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);
        cardView = (CardView) view.findViewById(R.id.tramiteDocumentarioCard);
        text1 = (TextView) view.findViewById(R.id.documentoTD);
        text2 = (TextView) view.findViewById(R.id.asuntoTD);
        text3 = (TextView) view.findViewById(R.id.firmaTD);
        text5 =(TextView) view.findViewById(R.id.fechaTD);
        text4=  (TextView) view.findViewById(R.id.registroTD);
    }
    @Override
    public void onClick(View v) {
        // The user may not set a click listener for list items, in which case our listener
        // will be null, so we need to check for this
        if (mOnEntryClickListener != null) {
            mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
        }
    }
}   private ArrayList<Expediente> mExpediente;
    private Context mContext;

    public TramiteDocumentarioAdapter(Context context, ArrayList<Expediente> expedienteArray) {
        mContext = context;
        mExpediente = expedienteArray;
    }
    @Override
    public TramiteDocumentarioAdapter.AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tramite_documentario, parent, false);
        return new TramiteDocumentarioAdapter.AdaptadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TramiteDocumentarioAdapter.AdaptadorViewHolder holder, int position) {
        Expediente exp = mExpediente.get(position);
        String asu = exp.getExpe_asunto();
        String fir = exp.getExpe_firma();
        String fec = exp.getExpe_fecha();
        String doc= exp.getTexp_descripcion()+" "+exp.getExpe_numero_doc()+exp.getExpe_siglas_doc();
        String reg=String.valueOf(exp.getExpe_id());
        holder.text1.setText(doc);
        holder.text2.setText(asu);
        holder.text3.setText(fir);
        holder.text4.setText(reg);
        holder.text5.setText(fec);
    }
    @Override
    public int getItemCount() {
        return mExpediente.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private TramiteDocumentarioAdapter.OnEntryClickListener mOnEntryClickListener;

public interface OnEntryClickListener {
    void onEntryClick(View view, int position);

}

    public void setOnEntryClickListener(TramiteDocumentarioAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}