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
    private Button resetGame;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (TextView) findViewById(R.id.player);

        resetGame = (Button) findViewById(R.id.reset_button);

        Button button01 = findViewById(R.id.button_1);
        Button button02 = findViewById(R.id.button_2);
        Button button03 = findViewById(R.id.button_3);
        Button button04 = findViewById(R.id.button_4);
        Button button05 = findViewById(R.id.button_5);
        Button button06 = findViewById(R.id.button_6);
        Button button07 = findViewById(R.id.button_7);
        Button button08 = findViewById(R.id.button_8);
        Button button09 = findViewById(R.id.button_9);

        button01.setOnClickListener(buttonAction);
        button02.setOnClickListener(buttonAction);
        button03.setOnClickListener(buttonAction);
        button04.setOnClickListener(buttonAction);
        button05.setOnClickListener(buttonAction);
        button06.setOnClickListener(buttonAction);
        button07.setOnClickListener(buttonAction);
        button08.setOnClickListener(buttonAction);
        button09.setOnClickListener(buttonAction);

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
                player.setText("Jugador uno es el ganador");
                thereIsAWinner = true;
            } else if(checkWinner() && activePlayerTwo){
                player.setText("Jugador dos es el ganador");
                thereIsAWinner = true;
            }

            activePlayerOne = !activePlayerOne;
            activePlayerTwo = !activePlayerTwo;
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


}