package r12.retrofitrssxmljson.GitHub;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import r12.retrofitrssxmljson.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubActivity extends AppCompatActivity {

    private TextView tvUserId;

    private String BASE_URL = "https://api.github.com/";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub);
        tvUserId = (TextView)findViewById(R.id.tvUserId);

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<GitHubUser> call = service.getUser("JakeWharton");
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                if(progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                tvUserId.setText(response.body().getAllInfo().toString());
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                if(progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                tvUserId.setText("Error "+ t.toString());
            }
        });
    }
}
