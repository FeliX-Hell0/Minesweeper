package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements onGridClick {

    private ArrayList<TextView> cell_tvs;
    RecyclerView gridRecyclerView;
    gridRecycler adaptor;
    game myGame;
    boolean leftClick = false;
    int flagCount = 4;

    private int clock = 0;
    private boolean running = false;

    boolean win = false;
    boolean lose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            clock = savedInstanceState.getInt("clock");
            running = savedInstanceState.getBoolean("running");
        }

        runTimer();

        TextView txt = findViewById(R.id.main_activity_mode);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"switched", Toast.LENGTH_SHORT).show();
                if(!leftClick) {
                    leftClick = true;
                    TextView myView = view.findViewById(R.id.main_activity_mode);
                    myView.setText("⛏");
                }
                else{
                    leftClick = false;
                    TextView myView = view.findViewById(R.id.main_activity_mode);
                    myView.setText("\uD83D\uDEA9");
                }
            }
        });

        gridRecyclerView = findViewById(R.id.activity_main_grids);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 8));
        myGame = new game();
        myGame.generateMines();
        adaptor = new gridRecycler(myGame.getMygrid(), this);
        gridRecyclerView.setAdapter(adaptor);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("clock", clock);
        savedInstanceState.putBoolean("running", running);
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.activity_main_time);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =clock/3600;
                int minutes = (clock%3600) / 60;
                int seconds = clock%60;
                String time = String.format("%02d", clock);
                timeView.setText(time);

                if (running) {
                    clock++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onGridClick(grid mygrid) {
        running = true;
        if(win){
            running = false;
            Intent intent = new Intent(this, resultActivity.class);
            TextView editText = (TextView) findViewById(R.id.activity_main_time);
            String message = editText.getText().toString();
            intent.putExtra("Message", message);
            startActivity(intent);
        }
        if (lose) {
            Intent intent = new Intent(this, resultActivity.class);
            String message = "You Lost!";
            intent.putExtra("Message", message);
            running = false;
            startActivity(intent);
        }

        //Toast.makeText(getApplicationContext(),"Clicked", Toast.LENGTH_SHORT).show();
        if(leftClick && mygrid.flagged == false){
            myGame.show(mygrid);
        }
        else if(!leftClick && flagCount >= 1){
            if(!mygrid.revealed) {
                int flag = myGame.flagged(mygrid);
                TextView txt = findViewById(R.id.activity_main_mineCount);
                if (flag == 1) {
                    flagCount--;
                    txt.setText(Integer.toString(flagCount));
                } else {
                    flagCount++;
                    txt.setText(Integer.toString(flagCount));
                }
            }
        }

        adaptor = new gridRecycler(myGame.getMygrid(), this);
        gridRecyclerView.setAdapter(adaptor);

        int winFlag = myGame.winCheck();
        if(winFlag == 1){
            win = true;
            running = false;
        }
        else if(winFlag == 2){
            lose = true;
            for(int i = 0; i < this.myGame.getMygrid().size(); i++){
                this.myGame.getMygrid().get(i).revealed = true;
                this.myGame.getMygrid().get(i).flagged = false;
            }
            adaptor = new gridRecycler(myGame.getMygrid(), this);
            gridRecyclerView.setAdapter(adaptor);
            running = false;
        }
    }

    public void startIntent(Intent intent){
        startActivity(intent);
    }

}