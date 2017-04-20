package com.example.eduardo.locmess;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class LocationFragment extends Fragment {

    FloatingActionButton fab;
    String[] teste = {"Almada", "Lisboa", "Oeiras", "Cacém", "Loures", "Setúbal", "Corroios", "Seixal", "Costa da Caparica", "Sesimbra", "Faro", "Coimbra", "Leiria"};
    private TrackGPS gps;
    double longitude;
    double latitude;

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

                Toast.makeText(getActivity(), teste[position], Toast.LENGTH_SHORT).show();
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("long clicked", "pos:" + position);
                return false;
            }
        });

        return rootView;
    }
}
