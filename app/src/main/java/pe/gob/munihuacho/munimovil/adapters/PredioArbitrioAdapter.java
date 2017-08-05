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
import pe.gob.munihuacho.munimovil.model.Predio;

/**
 * Created by alexisholyoak on 4/08/2017.
 */

public class PredioArbitrioAdapter extends RecyclerView.Adapter<PredioArbitrioAdapter.AdaptadorViewHolder>{
    public class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView text1, text2, text3,text4,text5;
        AdaptadorViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            cardView = (CardView) view.findViewById(R.id.tramiteDocumentarioCard);
            text1 = (TextView) view.findViewById(R.id.descrpcionPA);
            text2 = (TextView) view.findViewById(R.id.deudaPA);
            text3 = (TextView) view.findViewById(R.id.tasaPA);
            text5 =(TextView) view.findViewById(R.id.abonadoPA);
            text4=  (TextView) view.findViewById(R.id.saldoPA);
        }
        @Override
        public void onClick(View v) {
            // The user may not set a click listener for list items, in which case our listener
            // will be null, so we need to check for this
            if (mOnEntryClickListener != null) {
                mOnEntryClickListener.onEntryClick(v, getLayoutPosition());
            }
        }
    }   private ArrayList<Predio> mPredio;
    private Context mContext;

    public PredioArbitrioAdapter(Context context, ArrayList<Predio> predioArray) {
        mContext = context;
        mPredio = predioArray;
    }
    @Override
    public PredioArbitrioAdapter.AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.predios, parent, false);
        return new PredioArbitrioAdapter.AdaptadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PredioArbitrioAdapter.AdaptadorViewHolder holder, int position) {
        Predio pre = mPredio.get(position);
        String desc=pre.getDescrip();
        String deud=String.valueOf(pre.getNvdeuda());
        String tasas=String.valueOf(pre.getNvtasas());
        String abon=String.valueOf(pre.getNvabon());
        String sald=String.valueOf(pre.getSaldo());
        holder.text1.setText(desc);
        holder.text2.setText(deud);
        holder.text3.setText(tasas);
        holder.text4.setText(abon);
        holder.text5.setText(sald);
    }
    @Override
    public int getItemCount() {
        return mPredio.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private PredioArbitrioAdapter.OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);

    }

    public void setOnEntryClickListener(PredioArbitrioAdapter.OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }

}
