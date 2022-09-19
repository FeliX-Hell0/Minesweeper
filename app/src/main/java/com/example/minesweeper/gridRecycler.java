package com.example.minesweeper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class gridRecycler extends RecyclerView.Adapter<gridRecycler.MyViewHolder> {
    private List<grid> grids;
    private onGridClick click;

    public gridRecycler(List<grid> grids, onGridClick click){
        this.grids = grids;
        this.click = click;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gridValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gridValue = itemView.findViewById(R.id.custom_grid_value);
        }

        public void bind(grid grid){
            itemView.setBackgroundColor(Color.GREEN);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.onGridClick(grid);
                }
            });

            if(grid.flagged){
                gridValue.setText("\uD83D\uDEA9");
                itemView.setBackgroundColor(Color.GRAY);
            }
            else {

                if (grid.revealed) {
                    if (grid.mine) {
                        gridValue.setText(R.string.mine);
                        itemView.setBackgroundColor(Color.GRAY);
                    } else if (grid.value != 0) {
                        gridValue.setText(String.valueOf(grid.value));
                        itemView.setBackgroundColor(Color.GRAY);
                    } else {
                        itemView.setBackgroundColor(Color.WHITE);
                    }
                }
            }
        }
    }

    @NonNull
    @Override
    public gridRecycler.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View tv = li.inflate(R.layout.custom_grid_layout, parent, false);
        return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull gridRecycler.MyViewHolder holder, int position) {
        holder.bind(grids.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return grids.size();
    }
}
