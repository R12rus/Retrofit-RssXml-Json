package r12.retrofitrssxmljson.MovieDb.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import r12.retrofitrssxmljson.MovieDb.Model.Movie;
import r12.retrofitrssxmljson.R;

import static r12.retrofitrssxmljson.R.id.imageMovie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{

    private List<Movie> movies;
    private int rowLayout;
    private Context context;


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movies_layout) LinearLayout moviesLayout;
        @BindView(R.id.movieTitle)  TextView movieTitle;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.subtitle) TextView subtitle;
        @BindView(R.id.description) TextView movieDescription;
        @BindView(R.id.imageMovie) ImageView imageMovie;

        public MovieViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

    }

    public MoviesAdapter(List<Movie> movies,int rowLayout,Context context){
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }


    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.subtitle.setText(movies.get(position).getReleaseDate());
        holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPosterPath())
                    .asBitmap()
                    .into(holder.imageMovie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
