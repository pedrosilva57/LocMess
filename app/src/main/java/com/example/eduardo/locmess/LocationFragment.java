package com.example.eduardo.locmess;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class LocationFragment extends Fragment {

    String[] teste = {"Almada", "Lisboa", "Oeiras", "Cacém", "Loures", "Setúbal", "Corroios", "Seixal", "Costa da Caparica", "Sesimbra", "Faro", "Coimbra", "Leiria"};


    FloatingActionButton fab;
    double longitude;
    double latitude;
    private TrackGPS gps;

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_location, container, false);

        //Floating Action Button Action
        fab = (FloatingActionButton) rootView.findViewById(R.id.locatAdd);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), AddLocationActivity.class);
                startActivity(intent);

            }
        });

        final ListView lv = (ListView) rootView.findViewById(R.id.locationListView);
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, teste);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), EditLocationActivity.class);
                String listItem = (String)lv.getItemAtPosition(position);
                intent.putExtra("Local", listItem);
                startActivity(intent);

                //Toast.makeText(getActivity(), "Escolheu " + position, Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.v("long clicked", "pos:" + position);
                setHasOptionsMenu(true);//ActionBar Icons Actions
                return true;
            }
        });

        return rootView;
    }

    //ActionBar Icons
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.action_buttons, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:

                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
