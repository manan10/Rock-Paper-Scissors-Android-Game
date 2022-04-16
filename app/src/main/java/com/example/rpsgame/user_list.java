package com.example.rpsgame;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class user_list extends ArrayAdapter<Player> {
    private Activity context;
    private List<Player> p_list;

    public user_list(Activity context, List<Player> p_list)
    {
        super(context,R.layout.activity_history,p_list);
        this.context=context;
        this.p_list=p_list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.activity_history,null,true);
        TextView textViewcsore=(TextView) listviewitem.findViewById(R.id.text1);
        TextView textViewpscore=(TextView) listviewitem.findViewById(R.id.text2);

        Player player=p_list.get(position);

        textViewcsore.setText(String.valueOf(player.getCscore()));
        textViewpscore.setText(String.valueOf(player.getPscore()));


        return listviewitem;
    }
}
