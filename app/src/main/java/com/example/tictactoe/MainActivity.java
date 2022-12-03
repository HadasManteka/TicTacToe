package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static int countTaps = 0;
    boolean gameActive = true;

    //  1 - X
    //  0 - O
    //  2 - empty
    int activePlayer = 1;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // All the win positions
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                            {0, 4, 8}, {2, 4, 6}};

    // Every tap in an empty box of the grid
    public void playerTap(View view) {
        if (gameActive) {
            ImageView img = (ImageView) view;
            ImageView turnImg = findViewById(R.id.imageTurn);
            int tappedImage = Integer.parseInt(img.getTag().toString());

            writeTurn(turnImg);

            if (gameState[tappedImage] == 2) {
                countTaps++;
                gameState[tappedImage] = activePlayer;

                if (activePlayer == 1) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 0;
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 1;
                }
            }

            checkWinning(turnImg);

            if (countTaps == 9) {
                gameActive = false;
                Button playAgainButton = findViewById(R.id.play_again_button);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void writeTurn(ImageView img) {
        img.setImageResource((activePlayer == 1) ? R.drawable.oplay : R.drawable.xplay);
    }

    public void checkWinning(ImageView img) {
        int flag = 0;
        int winIndex = 0;

        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]] != 2) {

                gameActive = false;
                flag = 1;
                setMark(winIndex);
                img.setImageResource((gameState[winPosition[0]] == 1) ? R.drawable.xwin : R.drawable.owin);
                Button playAgainButton = findViewById(R.id.play_again_button);
                playAgainButton.setVisibility(View.VISIBLE);
            }
            winIndex++;
        }

        if (countTaps == 9 && flag == 0) {
            img.setImageResource(R.drawable.nowin);
        }
    }

    public void setMark(int winPosition) {
        ImageView markImg = findViewById(R.id.mark);
        int mark = R.drawable.empty;

        switch (winPosition) {
            case 0: {
                mark = R.drawable.mark6;
                break;
            }
            case 1: {
                mark = R.drawable.mark7;
                break;
            }
            case 2: {
                mark = R.drawable.mark8;
                break;
            }
            case 3: {
                mark = R.drawable.mark3;
                break;
            }
            case 4: {
                mark = R.drawable.mark4;
                break;
            }
            case 5: {
                mark = R.drawable.mark5;
                break;
            }
            case 6: {
                mark = R.drawable.mark1;
                break;
            }
            case 7: {
                mark = R.drawable.mark2;
                break;
            }
        }

        markImg.setImageResource(mark);
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 1;
        countTaps = 0;
        ImageView markImg = findViewById(R.id.mark);
        markImg.setImageResource(R.drawable.empty);

        ImageView turnImg = findViewById(R.id.imageTurn);
        turnImg.setImageResource(R.drawable.xplay);

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

        Button playAgainButton = findViewById(R.id.play_again_button);
        playAgainButton.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}