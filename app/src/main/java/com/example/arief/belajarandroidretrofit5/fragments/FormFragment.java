package com.example.arief.belajarandroidretrofit5.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit5.R;
import com.example.arief.belajarandroidretrofit5.retrofitHandler.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Arief on 8/19/2017.
 */

public class FormFragment extends Fragment implements ListFragment.ListFragmentListener{

    private EditText id,nama;
    private Button insert;


    private RecyclerView rec;



    private Button listener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form_fragment,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        id = (EditText)getActivity().findViewById(R.id.idPerson);
        nama = (EditText)getActivity().findViewById(R.id.namePerson);



        insert = (Button)getActivity().findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                if(Integer.parseInt(id.getText().toString())<=0 || nama.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(), "Must be valid input ", Toast.LENGTH_SHORT).show();
                }else{
                    kirimData(
                            Integer.parseInt(id.getText().toString()),nama.getText().toString().trim()
                    );
                    clearFields();
                }

                }catch (NumberFormatException ex){
                    Toast.makeText(getActivity(), "Must be Number for an id", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("Error ex",ex.getMessage());
                    Toast.makeText(getActivity(), "Oops , Something Wrond " + ex.getMessage() , Toast.LENGTH_SHORT).show();
                }
            }
        });


        listener  = (Button)getActivity().findViewById(R.id.listener);


        listener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragment main = new MainFragment();

                Bundle b = new Bundle();



                try{
                    b.putInt("id",Integer.parseInt(id.getText().toString()));
                }catch (NumberFormatException ex){
                    Toast.makeText(getActivity(), "Must be number for an id", Toast.LENGTH_SHORT).show();
                }

                main.setArguments(b);

                FragmentManager fm = getActivity().getSupportFragmentManager();

                fm.beginTransaction().replace(R.id.mainContainer,main).commit();

            }
        });

    }

    private void clearFields(){
        id.setText("");
        nama.setText("");
    }

    private void kirimData(int id ,String nama){
        Map<String,Object> map = new HashMap<>();

        map.put("id",id);
        map.put("nama",nama);

        new RetrofitClient(getActivity()).insert(map);
    }

    @Override
    public void setRecView(RecyclerView rec) {
        this.rec=rec;
    }
}
