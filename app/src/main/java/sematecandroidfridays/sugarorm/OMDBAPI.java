package sematecandroidfridays.sugarorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import Models.MovieModel;
import cz.msebera.android.httpclient.Header;

public class OMDBAPI extends AppCompatActivity {

    EditText name;
    Button showfilm;
    ImageView posterFilm;
    TextView title;
    String TAG = "respone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_omdbapi);

        name = (EditText) findViewById(R.id.name);
        showfilm = (Button) findViewById(R.id.showfilm);
        posterFilm = (ImageView) findViewById(R.id.filmposter);
        title = (TextView) findViewById(R.id.filmtitle);

        showfilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://www.omdbapi.com/?t=" + name.getText().toString();
                getData(url);
            }
        });
    }

    public void getData(String url) {
        AsyncHttpClient oClient = new AsyncHttpClient();
        oClient.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG,"getData : " + throwable.getMessage());
                Toast.makeText(OMDBAPI.this, "Error in response : " + throwable.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                setData(responseString);
//                Toast.makeText(OMDBAPI.this, responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setData(String response) {
        try {
            Gson gson = new Gson();
            MovieModel model = gson.fromJson(response,MovieModel.class);
            String posterURL = model.getPoster();
            String movieDirector = model.getDirector();

            Glide.with(OMDBAPI.this).load(posterURL).into(posterFilm);
            title.setText("Director : " + movieDirector);

        } catch (Exception e) {
            Toast.makeText(OMDBAPI.this, "Error in parse : " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
