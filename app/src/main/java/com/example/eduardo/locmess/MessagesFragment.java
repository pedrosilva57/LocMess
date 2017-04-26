package com.example.eduardo.locmess;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MessagesFragment extends Fragment {

    FloatingActionButton fab;
    String[] teste = {"SMS 1", "SMS 2", "SMS 3", "SMS 4", "SMS 5", "SMS 6", "SMS 7", "SMS 8", "SMS 9", "SMS 10", "SMS 11"};

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_messages, container, false);

        //Floating Action Button Action
        FloatingActionButton new_message_view = (FloatingActionButton) rootView.findViewById(R.id.new_message);
        new_message_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call CreateMessage Activity
                Intent i = new Intent(view.getContext(), CreateMessage.class);
                startActivity(i);
            }
        });

        ListView lv = (ListView) rootView.findViewById(R.id.messagesListView);
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, teste);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), teste[position], Toast.LENGTH_SHORT).show();
                
                //Call ShowMessage Activity
                Intent i = new Intent(view.getContext(), ShowMessage.class);
                i.putExtra("position",position);
                startActivity(i);
            }
        });
        return rootView;
        //return inflater.inflate(R.layout.fragment_messages, container, false);
    }
}
