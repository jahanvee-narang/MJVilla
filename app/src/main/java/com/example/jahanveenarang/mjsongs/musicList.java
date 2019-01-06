package com.example.jahanveenarang.mjsongs;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class musicList extends AppCompatActivity {


    private AdapterClass songAdapter;
    private RecyclerView recyclerView;
    private ProgressBar loader;
   TextView text;
    MainClassList results;
    List<ModalClass> dataListList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        recyclerView = findViewById(R.id.recyclrview);
          loader = findViewById(R.id.feed_loading);

          text = findViewById(R.id.text);
        if (isNetworkAvailable()) {
            loader.setVisibility(VISIBLE);
            get_data();

        } else {

            text.setText("Please wait while loading.....");
            text.setVisibility(VISIBLE);
            loader.setVisibility(GONE);

        }


    }

    private void get_data() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIinterface service = retrofit.create(APIinterface.class);
        Call<MainClassList> call = service.getdetail();
        call.enqueue(new Callback<MainClassList>() {
            @Override
            public void onResponse(Call<MainClassList> call, Response<MainClassList> response) {
                loader.setVisibility(GONE);
                if(response.isSuccessful()) {
                    dataListList = response.body().getResults();
                    Log.e("rrr", String.valueOf(dataListList.get(0).    getImage()));

                    songAdapter = new AdapterClass(getApplicationContext(),dataListList ,R.layout.layout_file);


                  //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(musicList.this);

                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                    recyclerView.setAdapter(songAdapter);
                }else{

                    loader.setVisibility(GONE);
                    Toast.makeText(getApplicationContext(), "Connection Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MainClassList> call, Throwable t) {
                loader.setVisibility(GONE);
                Log.e("faill",t.toString());
                Toast.makeText(getApplicationContext(),"Connection Failed", Toast.LENGTH_SHORT).show();
            }


        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
