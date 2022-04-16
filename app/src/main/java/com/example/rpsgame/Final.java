package com.example.rpsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        Intent i=getIntent();
        String score=i.getStringExtra("score");
        String status=i.getStringExtra("status");

        TextView finalScore=findViewById(R.id.score);
        TextView finalStatus=findViewById(R.id.status);

        finalScore.setText(score);
        finalStatus.setText(status);

        final Button history=findViewById(R.id.history);

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Final.this,history.class);
                startActivity(i);


            }
        });

    }
}
