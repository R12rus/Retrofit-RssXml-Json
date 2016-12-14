package r12.retrofitrssxmljson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import r12.retrofitrssxmljson.GitHub.GitHubActivity;
import r12.retrofitrssxmljson.GitHub.GitHubUser;
import r12.retrofitrssxmljson.MovieDb.MovieDbActivity;
import r12.retrofitrssxmljson.RssXml.RssXmlActivity;
import r12.retrofitrssxmljson.Translate.TranslateActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnMovieDb) Button btnMovieDb;
    @BindView(R.id.btnRssXml) Button btnRssXml;
    @BindView(R.id.btnGitHub) Button btnGitHub;
    @BindView(R.id.btnTranslate) Button btnTranslate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btnMovieDb)
    public void onClickBtnMovieDb(){
        Intent intent = new Intent(MainActivity.this, MovieDbActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnRssXml)
    public void onClickRssXml(){
        Intent intent = new Intent(MainActivity.this, RssXmlActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnGitHub)
    public void onClickGitHub(){
        Intent intent = new Intent(MainActivity.this, GitHubActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btnTranslate)
    public void onClickTranslate(){
        Intent intent = new Intent(MainActivity.this, TranslateActivity.class);
        startActivity(intent);
    }
}
