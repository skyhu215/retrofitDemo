package com.example.retrofitdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.retrofitdemo.model.ReposModule;
import com.example.retrofitdemo.service.GitHubService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_request =this.findViewById(R.id.button);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

    }


    private void request() {
        GitHubService requestService = RetrofitClient.getInstance().getRetrofit().create(GitHubService.class);
        Call<List<ReposModule>> call = requestService.listRepos("1");

        //同步方法
        //executeMethod(call);

        //异步方法
        call.enqueue(new retrofit2.Callback<List<ReposModule>>() {
            @Override
            public void onResponse(Call<List<ReposModule>> call, retrofit2.Response<List<ReposModule>> response) {
                // 数据返回成功
                Log.i(TAG, "onResponse: success ");
                List<ReposModule> list = response.body();
                Log.d(TAG, new Gson().toJson(list));

            }

            @Override
            public void onFailure(Call<List<ReposModule>> call, Throwable t) {
                // 数据返回失败
                Log.i(TAG, "onFailure: fail"+t.getMessage());
            }
        });





    }


    public void executeMethod(Call<ResponseBody> call) {
        // 同步
        try {
            ResponseBody repos = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}