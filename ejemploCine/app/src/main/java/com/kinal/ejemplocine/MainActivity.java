package com.kinal.ejemplocine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kinal.ejemplocine.Interface.PeliculaService;
import com.kinal.ejemplocine.Model.Pelicula;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtId, txtTitulo, txtSinopsi, txtTriler, txtImg, txtRate, txtGenero;

    ImageView imageView;

    ArrayList<Pelicula> arrayList = new ArrayList<>();

    int validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtId = (TextView) findViewById(R.id.textId);
        txtTitulo = (TextView) findViewById(R.id.textTitulo);
        txtSinopsi = (TextView) findViewById(R.id.textSinopsi);
        txtTriler = (TextView) findViewById(R.id.textTriler);
        txtImg = (TextView) findViewById(R.id.textImg);
        txtRate = (TextView) findViewById(R.id.textRate);
        txtGenero = (TextView) findViewById(R.id.textGenero);

        imageView = (ImageView)findViewById(R.id.imageView);

        if (!connectInternet()) {
            Toast.makeText(this, "Verifique su acceso a internet", Toast.LENGTH_SHORT).show();
            return;
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("Url").build();
        PeliculaService service = restAdapter.create(PeliculaService.class);
        service.getPelicula(new Callback<List<Pelicula>>() {
            @Override
            public void success(List<Pelicula> peliculas, Response response) {
                for (int i = 0; i < peliculas.size(); i++) {
                    Pelicula current = new Pelicula();

                    current.setId(peliculas.get(i).getId());
                    current.setTitulo(peliculas.get(i).getTitulo());
                    current.setSinopsis(peliculas.get(i).getSinopsis());
                    current.setTrailer_url(peliculas.get(i).getTrailer_url());
                    current.setImage(peliculas.get(i).getImage());
                    current.setRated(peliculas.get(i).getRated());
                    current.setGenero(peliculas.get(i).getGenero());

                    arrayList.add(current);

                }
                if (arrayList.size() > 0) {
                    txtId.setText(String.valueOf(arrayList.get(7).getId()));
                    txtTitulo.setText(arrayList.get(7).getTitulo());
                    txtSinopsi.setText(arrayList.get(7).getSinopsis());
                    txtTriler.setText(arrayList.get(7).getTrailer_url());
                    txtImg.setText(arrayList.get(7).getImage());
                    txtRate.setText(arrayList.get(7).getRated());
                    txtGenero.setText(arrayList.get(7).getGenero());

                    imageView.setImageBitmap(getBitmapFromURL(arrayList.get(7).getImage()));
                }

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplication(), retrofitError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    public boolean connectInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 );
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream input = conn.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }
}
