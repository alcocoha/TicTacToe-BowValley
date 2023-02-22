package com.example.assignment_01_tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView player;
    private Button[] buttons = new Button[9];

    boolean activePlayerOne = true;
    boolean activePlayerTwo = false;

    // The initial state of the game is declared where 0 equals 0, 1 equals X, and 2 equals empty.
    int [] gameState = {2,2,2,2,2,2,2,2,2};

    // The positions that will win are established from the beginning
    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // these are the matches for the winning rows
            {0,3,6}, {1,4,7}, {2,5,8}, //these are the matches for the winning columns
            {0,4,8}, {2,4,6} // these are the matches for the winning diagonals
    };

    boolean thereIsAWinner = false;

    Button button01;
    Button button02;
    Button button03;
    Button button04;
    Button button05;
    Button button06;
    Button button07;
    Button button08;
    Button button09;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (TextView) findViewById(R.id.player);

        button01 = findViewById(R.id.button_1);
        button02 = findViewById(R.id.button_2);
        button03 = findViewById(R.id.button_3);
        button04 = findViewById(R.id.button_4);
        button05 = findViewById(R.id.button_5);
        button06 = findViewById(R.id.button_6);
        button07 = findViewById(R.id.button_7);
        button08 = findViewById(R.id.button_8);
        button09 = findViewById(R.id.button_9);
        Button resetButton = findViewById(R.id.reset_button);

        button01.setOnClickListener(buttonAction);
        button02.setOnClickListener(buttonAction);
        button03.setOnClickListener(buttonAction);
        button04.setOnClickListener(buttonAction);
        button05.setOnClickListener(buttonAction);
        button06.setOnClickListener(buttonAction);
        button07.setOnClickListener(buttonAction);
        button08.setOnClickListener(buttonAction);
        button09.setOnClickListener(buttonAction);
        resetButton.setOnClickListener(resetGame);

    }


    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(thereIsAWinner){
                return;
            }

            int index = Integer.parseInt(v.getTag().toString());

            Button selectedButton = (Button) v;
            if(activePlayerOne) {
                selectedButton.setText("X");
                selectedButton.setTextColor(Color.parseColor("#ff0000"));
                gameState[index] = 0;
            } else if(activePlayerTwo){
                selectedButton.setText("O");
                selectedButton.setTextColor(Color.parseColor("#ffff00"));
                gameState[index] = 1;
            }

            if(checkWinner() && activePlayerOne){
                player.setText("Player one is winner!");
                thereIsAWinner = true;
            } else if(checkWinner() && activePlayerTwo){
                player.setText("Player two is winner!");
                thereIsAWinner = true;
            }

            activePlayerOne = !activePlayerOne;
            activePlayerTwo = !activePlayerTwo;

            if(!thereIsAWinner){
                if(activePlayerOne){
                    player.setText("Player one turn");
                } else if(activePlayerTwo){
                    player.setText("Player two turn");
                }
            }
        }
    };

    public boolean checkWinner(){
        boolean isWinner = false;

        for(int [] winningPosition: winningPositions){

            if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]] != 2){
                isWinner = true;
            }
        }

        return isWinner;
    }


    View.OnClickListener resetGame = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            activePlayerOne = true;
            activePlayerTwo = false;
            thereIsAWinner = false;
            button01.setText("");
            button02.setText("");
            button03.setText("");
            button04.setText("");
            button05.setText("");
            button06.setText("");
            button07.setText("");
            button08.setText("");
            button09.setText("");
            player.setText("Player one starts the game");
            for(int i = 0; i < buttons.length; i++){
                gameState[i] = 2;
            }
        }
    };


}