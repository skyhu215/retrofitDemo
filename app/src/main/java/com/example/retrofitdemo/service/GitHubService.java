package com.example.retrofitdemo.service;

import com.example.retrofitdemo.model.ReposModule;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {

	@GET("users/{user}/repos")
	Call<List<ReposModule>> listRepos(@Path("user") String user);
}
