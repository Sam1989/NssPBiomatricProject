package com.skj.nsspproject.webservice;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {

    @GET
    Call<ResponseBody> getApi(@Url String url);
}
