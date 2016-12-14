package r12.retrofitrssxmljson.RssXml;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import r12.retrofitrssxmljson.R;
import r12.retrofitrssxmljson.RssXml.ModelRss.Item;
import r12.retrofitrssxmljson.RssXml.ModelRss.Rss;
import r12.retrofitrssxmljson.RssXml.RecyclerViewAdapterRssXml.RssXmlAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RssXmlActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://feeds.bbci.co.uk/news/";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private ArrayList<Item> arrayList;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_xml);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerViewRss);
        linearLayoutManager =
                new LinearLayoutManager(RssXmlActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();

        RssXmlInterface rss = retrofit.create(RssXmlInterface.class);
        Call<Rss> call = rss.getRssItems();
        call.enqueue(new Callback<Rss>() {
            @Override
            public void onResponse(Call<Rss> call, Response<Rss> response) {
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Rss rss = response.body();
                arrayList = rss.getChannel().getItems();
                RssXmlAdapter adapter = new RssXmlAdapter
                        (arrayList,R.layout.list_item_rssxml,getApplicationContext());
                recyclerView.setAdapter(adapter);

                DividerItemDecoration mDividerItemDecoration =
                        new DividerItemDecoration(
                                recyclerView.getContext(),
                                linearLayoutManager.getOrientation()
                        );
                recyclerView.addItemDecoration(mDividerItemDecoration);

            }

            @Override
            public void onFailure(Call<Rss> call, Throwable t) {
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                Toast.makeText(RssXmlActivity.this,"Error " + t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
}
