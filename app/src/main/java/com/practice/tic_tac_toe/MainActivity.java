package com.practice.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 - snake
    // 1 - snake charmer
    // 2 - blank
    boolean gameActive = true;
    int activePlayer = 0;
    int tapped = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    public void onTap(View view) {
        ImageView img = (ImageView) view;
        int tap = Integer.parseInt(img.getTag().toString());
        if (!gameActive) {
            gameReset();
        }
        else {
            if (gameState[tap] == 2) {
                tapped++;
                gameState[tap] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.snake);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("SNAKE CHARMER's Turn - Tap to Play!");
                }
                else {
                    img.setImageResource(R.drawable.snakecharmer);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("SNAKE's Turn - Tap to Play!");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
            for (int winPosition[] : winPositions) //to check for win
            {
                if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]
                        && gameState[winPosition[0]] != 2) {
                    //somebody has won
                    String winnerstr;
                    if (gameState[winPosition[0]] == 0) {
                        winnerstr = "SNAKE HAS WON!";
                    }
                    else {
                        winnerstr = "SNAKE CHARMER HAS WON!";
                    }
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerstr + "\nTap anywhere to reset");
                    gameActive = false;
                    break;
                }
            }
            if (tapped == 9 && gameActive) {
                TextView status = findViewById(R.id.status);
                status.setText("DRAW!\nTap anywhere to reset");
                gameActive = false;
            }
        }
    }

    public void gameReset() {
        gameActive = true;
        activePlayer = 0;
        tapped = 0;
        for (int x=0;x<9;x++) {
            gameState[x] = 2;
        }
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("SNAKE's Turn - Tap to Play!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}