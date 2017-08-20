package com.example.arief.belajarandroidretrofit5.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arief.belajarandroidretrofit5.R;
import com.example.arief.belajarandroidretrofit5.adapters.RecViewAdapter;
import com.example.arief.belajarandroidretrofit5.model.Person;
import com.example.arief.belajarandroidretrofit5.retrofitHandler.RetrofitClient;

import java.util.ArrayList;

/**
 * Created by Arief on 8/19/2017.
 */

public class ListFragment extends Fragment {

    private RecyclerView recView;

    private RecyclerView.LayoutManager lat;


    private RecViewAdapter adapter;

    private View vFrag;

    private ListFragmentListener listener;

    public interface ListFragmentListener{
        void setRecView(RecyclerView rec);
    }



    public View getvFrag() {
        return vFrag;
    }

    public void setvFrag(View vFrag) {
        this.vFrag = vFrag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =
                inflater.inflate(R.layout.list_fragment,container,false);

        setvFrag(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Person> data = new ArrayList<>();

        recView = (RecyclerView)getActivity().findViewById(R.id.recView);

        //listener = (ListFragmentListener)getActivity();

        listener.setRecView(recView);



        lat = new LinearLayoutManager(getActivity());

        adapter = new RecViewAdapter(data);

        new RetrofitClient(getActivity()).loadData(recView,adapter,lat,data);

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (ListFragmentListener)context;
    }
}
