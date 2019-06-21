package ir.radicalcode.app.bmi.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ir.radicalcode.app.bmi.R;
import ir.radicalcode.app.bmi.data.entity.ArticleModel;
import ir.radicalcode.app.bmi.view.interfaces.OnArticleItemClickListener;

public class ArticleItemAdapter extends RecyclerView.Adapter<ArticleItemAdapter.ViewHolder> {

    private static OnArticleItemClickListener listener;
    private static List<ArticleModel> list;

    public ArticleItemAdapter( OnArticleItemClickListener listener , List<ArticleModel> list ) {
        this.listener = listener;
        this.list = list;
    }

    @NonNull
    @Override
    public ArticleItemAdapter.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent , int viewType ) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.adapter_item_article , parent , false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder( @NonNull ArticleItemAdapter.ViewHolder holder , int position ) {
        holder.title.setText( list.get( position ).getTitle() );
        byte[] pic = list.get( position ).getPic();
        Bitmap bitmap = BitmapFactory.decodeByteArray( pic , 0 , pic.length );
        holder.pic.setImageBitmap( bitmap );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtAdapterItemArtTitle)
        TextView title;
        @BindView(R.id.imgAdapterItemArt)
        ImageView pic;

        private ViewHolder( @NonNull View itemView ) {
            super( itemView );
            ButterKnife.bind( this , itemView );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick( View v ) {
            listener.onItemClick( list.get( getAdapterPosition() ) );
        }
    }
}
