package r12.retrofitrssxmljson.Translate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import r12.retrofitrssxmljson.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateActivity extends AppCompatActivity {
    @BindView(R.id.btnYandex) Button btnYandex;
    @BindView(R.id.etYandex) EditText etYandex;
    @BindView(R.id.tvYandex) TextView tvYandex;

    private final static String BASE_URL = "https://translate.yandex.net";
    private final static String API_KEY =
            "trnsl.1.1.20161128T085715Z.df7895ced2cc9b2a.0e7783fd417f5b93e4b041ba1f2b9f838dd3675e";

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnYandex)
    public void onClick() {
        Map<String,String> mapJson = new HashMap<String,String>();
        mapJson.put("key",API_KEY);
        mapJson.put("text",etYandex.getText().toString());
        mapJson.put("lang","en-ru");

        TranslateInterface translateInterface = retrofit.create(TranslateInterface.class);
        Call<Object> call = translateInterface.translate(mapJson);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                try{
                    Map<String,String> map = gson.fromJson(response.body().toString(),Map.class);
                    for (Map.Entry e : map.entrySet()){
                    if(e.getKey().equals("text")){
                        tvYandex.setText(e.getValue().toString());
                        }
                    }
                } catch (Exception exc){
                    tvYandex.setText(" " + exc.toString());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                tvYandex.setText(" " + t.toString());
            }
        });

    }
}
