package com.example.arief.belajarandroidretrofit5.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.arief.belajarandroidretrofit5.R;
import com.example.arief.belajarandroidretrofit5.fragments.MainFragment;
import com.example.arief.belajarandroidretrofit5.model.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arief on 8/19/2017.
 */

public class RecViewAdapter  extends RecyclerView.Adapter<RecViewAdapter.RecHolder> {

    private List<Person> data;

    public RecViewAdapter(ArrayList<Person> data){
        this.data=data;
    }

    @Override
    public RecHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_ui,parent,false);

        return new RecHolder(v);
    }


    @Override
    public void onBindViewHolder(final RecHolder holder, int position) {
        Person p = data.get(position);

        holder.tampilId.setText(String.valueOf(p.getId()));

        holder.tampilNama.setText(p.getName());

        final Context c= holder.tampilId.getContext();

        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm  = ((AppCompatActivity)c).getSupportFragmentManager();
                MainFragment main = new MainFragment();
                Bundle b = new Bundle();

                b.putInt("id",Integer.parseInt(holder.tampilId.getText().toString()));

                main.setArguments(b);

                fm.beginTransaction().replace(R.id.mainContainer,main).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecHolder extends RecyclerView.ViewHolder{

        private TextView tampilId,tampilNama;
        private Button details;

        public RecHolder(View itemView) {
            super(itemView);
            tampilId = (TextView)itemView.findViewById(R.id.tampilId);
            tampilNama = (TextView)itemView.findViewById(R.id.tampilNama);
            details = (Button)itemView.findViewById(R.id.details);
        }
    }
}
