package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onGridClick {

    private ArrayList<TextView> cell_tvs;
    RecyclerView gridRecyclerView;
    gridRecycler adaptor;
    game myGame;
    boolean leftClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        adaptor = new gridRecycler(myGame.getMygrid(), this);
        gridRecyclerView.setAdapter(adaptor);
    }
}