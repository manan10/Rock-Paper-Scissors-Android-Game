package com.example.rpsgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    List<Player> playerlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        databaseReference= FirebaseDatabase.getInstance().getReference("Player");
        listView=(ListView)findViewById(R.id.listview);
        playerlist=new ArrayList<>();

    }
    protected void onStart() {
        super.onStart();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                playerlist.clear();
                for(DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    Player player=userSnapshot.getValue(Player.class);
                    playerlist.add(player);
                }

                com.example.rpsgame.user_list adapter=new user_list(history.this,playerlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
