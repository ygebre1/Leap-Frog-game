package com.example.game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class GameWinScreen extends AppCompatActivity {
    private Button restart;
    private Button exit;
    private TextView score;
    private TextView congrats;
    private String points = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamewin);

        try {
            FileInputStream file = openFileInput("score.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                points += line;
            }
            reader.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        score = findViewById(R.id.score);
        score.setText("Score: " + points);

        congrats = findViewById(R.id.congrats);
        congrats.setText("You won!");

        restart = findViewById(R.id.restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfigScreen();
            }
        });

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitGame();
            }
        });
    }
    public void openConfigScreen() {
        Intent intent = new Intent(this, ConfigScreen.class);
        startActivity(intent);
    }

    public void exitGame() {
        finishAffinity();
    } // exitGame
}