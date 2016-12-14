package r12.retrofitrssxmljson.MovieDb;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import r12.retrofitrssxmljson.MovieDb.Model.Movie;
import r12.retrofitrssxmljson.MovieDb.Model.MoviesResponse;
import r12.retrofitrssxmljson.MovieDb.RecyclerViewAdapter.MoviesAdapter;
import r12.retrofitrssxmljson.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/*
The Movie Database API - https://developers.themoviedb.org/3/getting-started
BASE_URL = http://api.themoviedb.org/3/movie/top_rated?api_key=INSERT_YOUR_API_KEY
 */

public class MovieDbActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressDialog dialog;

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private final static String API_KEY = "43538d1b9fec890d9bdb793a870c6a03";

    private static Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_db);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();

        MovieInterface movieInterface = retrofit.create(MovieInterface.class);
        Call<MoviesResponse> call = movieInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                List<Movie> movies = response.body().getResults();
                MoviesAdapter adapter = new MoviesAdapter
                        (movies,R.layout.list_item_movie,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                if (dialog.isShowing()){
                    dialog.dismiss();
                }
                Toast.makeText(MovieDbActivity.this,"Error "  + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
