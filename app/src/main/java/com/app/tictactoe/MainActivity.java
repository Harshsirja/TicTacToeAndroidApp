package com.app.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Player Representation =>0 - X , 1 - O

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    //State meaning => 0 - (X) , 1 - (0) , 2 - BlankBox in grid

    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {2, 4, 6}, {0, 4, 8}};
    boolean gameActive = true;
    String winner = "";

    int filledBoxCount = 0;
    TextView result;
    Button reset;

    //0-0
    //1-X
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.resultTextbox);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void tap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());


        if (!gameActive) {
            reset();
        }

        if (gameState[tappedImage] == 2) {
            filledBoxCount++;
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.turn0);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText(R.string.turnx);
            }
            img.animate().translationYBy(1000f).setDuration(400);
        }

        //Check if any Player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                //SomeBody has won
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winner = "Winner is - X";
                } else if (gameState[winPosition[0]] == 1) {
                    winner = "Winner is - 0";
                }
            }
        }

        if (filledBoxCount == 9 && winner.isEmpty()) {
            winner = "Draw";
            result.setText(winner);
        } else if(!gameActive){
            //Update Status
            result.setText(winner);
        }
    }


    private void reset() {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText(R.string.turnx);
        result.setText(R.string.winner);
    }
}
