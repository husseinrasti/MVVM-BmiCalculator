package ir.radicalcode.app.bmi.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.BmiModel;

public class BmiDataAdapter extends RecyclerView.Adapter<BmiDataAdapter.ViewHolder> {

    private List<BmiModel> list = new ArrayList<>();
    protected static Context context;

    public BmiDataAdapter( Context context ) {
        this.context = context;
    }

    public void setList( List<BmiModel> list ) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent , int viewType ) {
        ViewGroup viewGroup = ( ViewGroup ) LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.adapter_item_history , parent , false );
        return new ViewHolder( viewGroup );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder , int position ) {
        holder.txtItemAdapterAge.setText( "سن: " + list.get( position ).getAge() );
        holder.txtItemAdapterDate.setText( "تاریخ: " + list.get( position ).getDate() );
        holder.txtItemAdapterBmiResult.setText( "نتیجه: " + String.valueOf( list.get( position ).getResult() ) );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtItemAdapterDate)
        TextView txtItemAdapterDate;
        @BindView(R.id.txtItemAdapterAge)
        TextView txtItemAdapterAge;
        @BindView(R.id.txtItemAdapterBmiResult)
        TextView txtItemAdapterBmiResult;
        @BindView(R.id.btnItemAdapterDelete)
        Button btnItemAdapterDelete;

        private ViewHolder( @NonNull View itemView ) {
            super( itemView );
            ButterKnife.bind( this , itemView );

            btnItemAdapterDelete.setOnClickListener( this );
        }

        @Override
        public void onClick( View v ) {
            AlertDialog.Builder builder = new AlertDialog.Builder( context );
            builder.setTitle( "حذف" );
            builder.setMessage( "آیا قصد حذف کردن این نتیجه را دارید؟ " );
            builder.setNegativeButton( "خیر" ,
                    ( dialog , which ) -> {
                    } );
            builder.setPositiveButton( "بله" ,
                    ( dialog , which ) -> {
                    } );
            builder.show();
        }
    }
}
