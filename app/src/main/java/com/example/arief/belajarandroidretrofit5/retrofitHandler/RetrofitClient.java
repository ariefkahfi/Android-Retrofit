package com.example.arief.belajarandroidretrofit5.retrofitHandler;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit5.adapters.RecViewAdapter;
import com.example.arief.belajarandroidretrofit5.model.Person;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Arief on 8/19/2017.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://192.168.1.100/AndroidRetrofit5/";

    private Context con;

    public RetrofitClient(){}

    public RetrofitClient(Context con){
        this.con=con;
    }

    public static Retrofit RetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void insert(Map<String,Object> map){
        RetrofitInstance()
                .create(RetrofitAPI.class)
                .insertAPI(map)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try {
                                Toast.makeText(con, response.body().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Error insert",t.getMessage());
                    }
                });
    }
    public void loadData(RecyclerView rec, final RecViewAdapter adapter, RecyclerView.LayoutManager lat, final ArrayList<Person> data) {

        rec.setHasFixedSize(true);
        rec.setLayoutManager(lat);
        rec.setAdapter(adapter);

        RetrofitInstance()
                .create(RetrofitAPI.class)
                .getAll()
                .enqueue(new Callback<JsonArray>() {
                    @Override
                    public void onResponse(Call<JsonArray> call, retrofit2.Response<JsonArray> response) {
                        data.clear();
                        for(JsonElement el : response.body()){
                           int id = el.getAsJsonObject().get("id").getAsInt();
                           String name = el.getAsJsonObject().get("nama").getAsString();

                           data.add(new Person(id,name));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<JsonArray> call, Throwable t) {
                        Log.e("Error Load Data",t.getMessage());
                    }
                });
    }


   public void loadDataById(int id){
       RetrofitInstance()
               .create(RetrofitAPI.class)
               .findById(id)
               .enqueue(new Callback<JsonObject>() {
                   @Override
                   public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                        if(response.isSuccessful()){
                            try{
                                JsonObject o = response.body();
                                Person p = new Person(o.get("id").getAsInt(),o.get("nama").getAsString());
                                buatDialog(p.toString());
                            }catch (Exception ex){
                                Log.e("Error caused by , ",ex.getMessage());
                                ex.printStackTrace();
                            }
                        }
                   }

                   @Override
                   public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("Error LoadById",t.getMessage());
                       Toast.makeText(con, "Data tidak ada dalam database kami", Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                   }

               });
   }

   private void buatDialog(String m){
       AlertDialog.Builder dialog =new AlertDialog.Builder(con);
       dialog.setTitle("Searching Result");

       dialog.setMessage(m);

       dialog.setNeutralButton("close",null);

       dialog.show();
   }

}
