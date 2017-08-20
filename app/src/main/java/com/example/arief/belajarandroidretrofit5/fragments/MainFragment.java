package com.example.arief.belajarandroidretrofit5.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit5.R;
import com.example.arief.belajarandroidretrofit5.retrofitHandler.RetrofitClient;

/**
 * Created by Arief on 8/19/2017.
 */

public class MainFragment extends Fragment {



    private EditText findText;

    private Button cari;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        findText = (EditText)getActivity().findViewById(R.id.findById);


        try{

            Bundle b = getArguments();

            int showId = b.getInt("id");

            if(showId!=0){
                findText.setText(String.valueOf(showId));
            }


        }catch (NullPointerException ex){
            findText.setText("");
        }catch (Exception ex){
            Toast.makeText(getActivity(), "Exceptiob here : " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }



        cari = (Button)getActivity().findViewById(R.id.cariPerson);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{


                    int id = Integer.parseInt(findText.getText().toString());

                    new RetrofitClient(getActivity()).loadDataById(id);

                }catch (NumberFormatException ex){
                    Log.e("Error number",ex.getMessage());
                    Toast.makeText(getActivity(), "Id must be a number", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Log.e("another Exception",ex.getMessage());
                }
            }
        });

     }


}
