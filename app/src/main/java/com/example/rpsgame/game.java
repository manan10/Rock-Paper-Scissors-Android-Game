package com.example.rpsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class game extends AppCompatActivity {

    TextView player_name,player_score,comp_score;
    ImageView player,computer,stone,paper,scisssor;
    String player_choice, computer_choice, result, name;
    Button finish;

    DatabaseReference ref;
    FirebaseDatabase database;

    int score;
    Random r;
    int pscore,cscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player_name=findViewById(R.id.player);

        player=findViewById(R.id.player_image);
        computer=findViewById(R.id.computer_image);

        stone=findViewById(R.id.stone);
        paper=findViewById(R.id.paper);
        scisssor=findViewById(R.id.scissors);

        database = FirebaseDatabase.getInstance();
        ref=database.getReference("Player");


        r=new Random();

        stone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.mipmap.rock);
                player_choice="stone";
                calculate();
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.mipmap.paper);
                player_choice="paper";
                calculate();
            }
        });
        scisssor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setImageResource(R.mipmap.scissors);
                player_choice="scissors";
                calculate();
            };
        });

        finish=findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player_score=findViewById(R.id.player_score);
                comp_score=findViewById(R.id.computer_score);
                String status;
                int pscore,cscore;

                pscore=Integer.parseInt(player_score.getText().toString());
                cscore=Integer.parseInt(comp_score.getText().toString());
                if(pscore>cscore)
                    status="You Win!!";
                else if(pscore==cscore)
                    status="Match Tie";
                else
                    status="You Lose!!";

                Intent i=new Intent(game.this,Final.class);
                i.putExtra("score",player_score.getText().toString());
                i.putExtra("status",status);
                getscore(cscore,pscore);
                startActivity(i);

            }
        });

    }
    public void getscore(int cscore,int pscore)
    {
        this.cscore=cscore;
        this.pscore=pscore;

        String id = ref.push().getKey();
        Player player = new Player(cscore,pscore);
        ref.child(id).setValue(player);
        //Toast.makeText(game.this, "Added", Toast.LENGTH_SHORT).show();
    }

    public void calculate()
    {
        player_score=findViewById(R.id.player_score);
        comp_score=findViewById(R.id.computer_score);
        name="You";


        pscore=Integer.parseInt(player_score.getText().toString());
        cscore=Integer.parseInt(comp_score.getText().toString());

        int comp=r.nextInt(3);
        if(comp==0) {
            computer_choice = "stone";
            computer.setImageResource(R.mipmap.rock);
        }else if(comp==1)
        {
            computer_choice="paper";
            computer.setImageResource(R.mipmap.paper);
        }else if(comp==2)
        {
            computer.setImageResource(R.mipmap.scissors);
            computer_choice="scissors";
        }

        if(player_choice.equals("stone") && computer_choice.equals("paper"))
        {

            cscore++;
            result = name+" Lose!!";
        }
        if(player_choice.equals("stone") && computer_choice.equals("scissors"))
        {

                pscore++;
            result = name+" Win!!";
        }

        if(player_choice.equals("paper") && computer_choice.equals("stone"))
        {

                pscore++;
            result = name+" Win!!";
        }
        if(player_choice.equals("paper") && computer_choice.equals("scissors"))
        {

                cscore++;
            result = name+" Lose!!";
        }

        if(player_choice.equals("scissors") && computer_choice.equals("paper"))
        {

                pscore++;
            result = name+" Win!!";
        }
        if(player_choice.equals("scissors") && computer_choice.equals("stone"))
        {
                cscore++;
            result = name+" Lose!!";
        }

        player_score.setText(String.valueOf(pscore));
        comp_score.setText(String.valueOf(cscore));
        //Toast.makeText(game.this,result,Toast.LENGTH_SHORT).show();
    }

}
