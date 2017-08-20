package com.example.arief.belajarandroidretrofit5;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.arief.belajarandroidretrofit5.fragments.FormFragment;
import com.example.arief.belajarandroidretrofit5.fragments.ListFragment;
import com.example.arief.belajarandroidretrofit5.fragments.MainFragment;

public class MainActivity extends AppCompatActivity implements ListFragment.ListFragmentListener {


    private FragmentManager fm;

    private FragmentTransaction ft;

    private LinearLayout linear;

    private Toolbar toolbar;

    private ActionBar ab ;

    private ListFragment listFrag;

    private  RecyclerView rec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.mainTol);
        linear = (LinearLayout)findViewById(R.id.mainContainer);

        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        ab.setTitle("Main Fragment");
        ab.setSubtitle("Android Retrofit @Query Example");


        fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.mainContainer,new FormFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("form");
        menu.add("list");
        menu.add("main");
        menu.add("cek");
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;
        if(item.getTitle() == "form"){
            FormFragment form = new FormFragment();

            form.setRecView(rec);

            ft = fm.beginTransaction();
            ft.replace(R.id.mainContainer,form).commit();

            ab.setTitle("Form Fragment");

        }else if(item.getTitle() == "list"){
            fragment = new ListFragment();
            ft = fm.beginTransaction();
            ft.replace(R.id.mainContainer,fragment,"list").commit();

            ab.setTitle("List Fragment");

        }else if (item.getTitle() == "main"){
            fragment = new MainFragment();
            ft = fm.beginTransaction();
            ft.replace(R.id.mainContainer,fragment).commit();

            ab.setTitle("Main Fragment");

        }else{
            RecyclerView rec = this.rec;
            if(rec == null){
                Toast.makeText(this, "NULL", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "IDAK NULL", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setRecView(RecyclerView rec) {
        this.rec = rec;
    }
}
