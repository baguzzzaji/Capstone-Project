package net.sebariskode.dramania;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import net.sebariskode.dramania.data.Drama;
import net.sebariskode.dramania.data.themoviedb.RetrofitHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by baguzzzaji on 26/10/2016.
 */

public class DramaAdapter extends RecyclerView.Adapter<DramaAdapter.ViewHolder> {
    static String TAG = DramaAdapter.class.getSimpleName();

    private Context context;
    private LayoutInflater inflater;
    private List<Drama> dramas;

    public DramaAdapter(Context context, List<Drama> dramas) {
        this.context = context;
        this.dramas = dramas;
        this.inflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.drama_title)
        TextView title;
        @BindView(R.id.drama_poster)
        ImageView dramaPoster;
        @BindView(R.id.item)
        FrameLayout item;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "onClick: True");
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.drama_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185";
        holder.title.setText(dramas.get(position).getName());

        Picasso.with(context)
                .load(BASE_POSTER_URL + dramas.get(position).getPoster_path())
                .into(holder.dramaPoster);

        Log.d(TAG, "showDramaItemRecyclerView: " + dramas.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dramas.size();
    }
}
