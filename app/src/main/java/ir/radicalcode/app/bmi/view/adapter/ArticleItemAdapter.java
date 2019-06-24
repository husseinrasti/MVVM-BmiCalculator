package ir.radicalcode.app.bmi.view.adapter;

import android.content.Context;
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
import ir.radicalcode.app.bmi.utils.Font;
import ir.radicalcode.app.bmi.view.interfaces.OnArticleItemClickListener;

public class ArticleItemAdapter extends RecyclerView.Adapter<ArticleItemAdapter.ViewHolder> {

    private static OnArticleItemClickListener mListener;
    private static List<ArticleModel> mList;
    private static Context mContext;

    public ArticleItemAdapter( Context context , OnArticleItemClickListener listener , List<ArticleModel> list ) {
        mListener = listener;
        mList = list;
        mContext = context;
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
        holder.title.setText( mList.get( position ).getTitle() );
        holder.pic.setImageResource( mList.get( position ).getPic() );
    }

    @Override
    public int getItemCount() {
        return mList.size();
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

            Font.getInstance( mContext ).iranSans( title );
        }

        @Override
        public void onClick( View v ) {
            mListener.onItemClick( mList.get( getAdapterPosition() ) );
        }
    }
}
