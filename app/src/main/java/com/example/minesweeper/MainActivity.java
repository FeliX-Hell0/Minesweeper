package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onGridClick {

    private ArrayList<TextView> cell_tvs;
    RecyclerView gridRecyclerView;
    gridRecycler adaptor;
    game myGame;
    boolean leftClick = false;
    int flagCount = 4;

    private int clock = 0;
    private boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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


    @Override
    public void onGridClick(grid mygrid) {
        Toast.makeText(getApplicationContext(),"Clicked", Toast.LENGTH_SHORT).show();
        if(leftClick){
            myGame.show(mygrid);
        }
        else{
            int flag = myGame.flagged(mygrid);
            TextView txt = findViewById(R.id.activity_main_mineCount);
            if(flag == 1){
                flagCount--;
                txt.setText(Integer.toString(flagCount));
            }else{
                flagCount++;
                txt.setText(Integer.toString(flagCount));
            }
        }

        adaptor = new gridRecycler(myGame.getMygrid(), this);
        gridRecyclerView.setAdapter(adaptor);

        int winFlag = myGame.winCheck();
        if(winFlag == 1){
            Intent intent = new Intent(this, resultActivity.class);
            TextView editText = (TextView) findViewById(R.id.activity_main_mineCount);
            String message = editText.getText().toString();
            intent.putExtra("Message", message);
            startActivity(intent);
        }
    }

}