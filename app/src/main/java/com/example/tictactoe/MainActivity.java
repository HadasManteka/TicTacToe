package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static int countTaps = 0;
    boolean gameActive = true;
    String message ="";

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
        TextView status = findViewById(R.id.status);
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset(view);
        }

        // if the tapped image is empty
        if (gameState[tappedImage] == 2) {
            countTaps++;
            gameState[tappedImage] = activePlayer;

            // check if its the last box
            if (countTaps == 9) {
                gameActive = false;
            }

            // Effects to the image
            img.setTranslationY(-1000f);
            img.animate().translationYBy(1000f).setDuration(300);

            if (activePlayer == 1) {
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                message = "O's Turn - Tap to play";
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                message = "X's Turn - Tap to play";
            }

            status.setText(message);
        }

//        checkWinning(status);
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 1;
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
        status.setText("X's Turn - Tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}