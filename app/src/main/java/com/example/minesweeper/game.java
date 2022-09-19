package com.example.minesweeper;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class game {
    private List<grid> mygrid;

    public game(){
        mygrid = new ArrayList<grid>();
        for(int i = 0; i < 80; i++){
            mygrid.add(new grid(i));
        }
    }

    public void generateMines(){
        ArrayList<Integer> randomList = new ArrayList<>();
        for(int i = 0; i < 80; i++){
            randomList.add(i);
        }
        Collections.shuffle(randomList);

        for(int j = 0; j < 4; j++){
            int i = randomList.get(j);
            this.mygrid.get(i).mine = true;
            int right = i + 1;
            int left = i - 1;
            int up = i - 8;
            int down = i + 8;

            if((i%8) == 0){
                mygrid.get(right).value ++;
                if(up >= 0){
                    mygrid.get(up).value ++;
                    mygrid.get(up+1).value ++;
                }
                if(down <= 79){
                    mygrid.get(down).value++;
                    mygrid.get(down+1).value++;
                }
            }
            else if((i%8) == 7){
                mygrid.get(left).value ++;
                if(up >= 0){
                    mygrid.get(up).value ++;
                    mygrid.get(up-1).value ++;
                }
                if(down <= 79){
                    mygrid.get(down).value++;
                    mygrid.get(down-1).value++;
                }
            }
            else{
                mygrid.get(left).value++;
                mygrid.get(right).value++;
                if(up >= 0){
                    mygrid.get(up).value ++;
                    mygrid.get(up-1).value ++;
                    mygrid.get(up+1).value ++;
                }
                if(down <= 79){
                    mygrid.get(down).value++;
                    mygrid.get(down+1).value++;
                    mygrid.get(down-1).value++;
                }
            }
        }
    }

    public void show(grid myGrid){
        myGrid.revealed = true;
        if(!myGrid.mine && (myGrid.value == 0)){
            adj(myGrid);
        }
    }

    public void adj(grid myGrid){
        int i = myGrid.index;
        List<grid> adjacent = new ArrayList<>();
        int right = i + 1;
        int left = i - 1;
        int up = i - 8;
        int down = i + 8;

        if((i%8) == 0){
            if(mygrid.get(right).mine != true) {
                if(!mygrid.get(right).revealed && (mygrid.get(right).value == 0)){
                    adjacent.add(mygrid.get(right));
                }
                mygrid.get(right).revealed = true;
            }
            if(up >= 0){
                if(mygrid.get(up).mine != true) {
                    if(!mygrid.get(up).revealed && (mygrid.get(up).value == 0)){
                        adjacent.add(mygrid.get(up));
                    }
                    mygrid.get(up).revealed = true;
                }
                if(mygrid.get(up+1).mine != true) {
                    if(!mygrid.get(up +1).revealed && (mygrid.get(up+1).value == 0)){
                        adjacent.add(mygrid.get(up + 1));
                    }
                    mygrid.get(up + 1).revealed = true;
                }
            }
            if(down <= 79){
                if(mygrid.get(down).mine != true) {
                    if(!mygrid.get(down).revealed && (mygrid.get(down).value == 0)){
                        adjacent.add(mygrid.get(down));
                    }
                    mygrid.get(down).revealed = true;
                }
                if(mygrid.get(down + 1).mine != true) {
                    if(!mygrid.get(down + 1).revealed && (mygrid.get(down+1).value == 0)){
                        adjacent.add(mygrid.get(down + 1));
                    }
                    mygrid.get(down + 1).revealed = true;
                }
            }
        }
        else if((i%8) == 7){
            if(mygrid.get(left).mine != true) {
                if(!mygrid.get(left).revealed && (mygrid.get(left).value == 0)){
                    adjacent.add(mygrid.get(left));
                }
                mygrid.get(left).revealed = true;
            }
            if(up >= 0){
                if(mygrid.get(up).mine != true) {
                    if(!mygrid.get(up).revealed && (mygrid.get(up).value == 0)){
                        adjacent.add(mygrid.get(up));
                    }
                    mygrid.get(up).revealed = true;
                }
                if(mygrid.get(up-1).mine != true) {
                    if(!mygrid.get(up-1).revealed && (mygrid.get(up-1).value == 0)){
                        adjacent.add(mygrid.get(up-1));
                    }
                    mygrid.get(up - 1).revealed = true;
                }
            }
            if(down <= 79){
                if(mygrid.get(down).mine != true) {
                    if(!mygrid.get(down).revealed && (mygrid.get(down).value == 0)){
                        adjacent.add(mygrid.get(down));
                    }
                    mygrid.get(down).revealed = true;
                }
                if(mygrid.get(down - 1).mine != true) {
                    if(!mygrid.get(down-1).revealed && (mygrid.get(down-1).value == 0)){
                        adjacent.add(mygrid.get(down-1));
                    }
                    mygrid.get(down - 1).revealed = true;
                }
            }
        }
        else{
            if(mygrid.get(left).mine != true) {
                if(!mygrid.get(left).revealed && (mygrid.get(left).value == 0)){
                    adjacent.add(mygrid.get(left));
                }
                mygrid.get(left).revealed = true;
            }
            if(mygrid.get(right).mine != true) {
                if(!mygrid.get(right).revealed && (mygrid.get(right).value == 0)){
                    adjacent.add(mygrid.get(right));
                }
                mygrid.get(right).revealed = true;
            }
            if(up >= 0){
                if(mygrid.get(up).mine != true) {
                    if(!mygrid.get(up).revealed && (mygrid.get(up).value == 0)){
                        adjacent.add(mygrid.get(up));
                    }
                    mygrid.get(up).revealed = true;
                }
                if(mygrid.get(up-1).mine != true) {
                    if(!mygrid.get(up-1).revealed && (mygrid.get(up-1).value == 0)){
                        adjacent.add(mygrid.get(up-1));
                    }
                    mygrid.get(up - 1).revealed = true;
                }
                if(mygrid.get(up+1).mine != true) {
                    if(!mygrid.get(up+1).revealed && (mygrid.get(up+1).value == 0)){
                        adjacent.add(mygrid.get(up+1));
                    }
                    mygrid.get(up + 1).revealed = true;
                }
            }
            if(down <= 79){
                if(mygrid.get(down).mine != true) {
                    if(!mygrid.get(down).revealed && (mygrid.get(down).value == 0)){
                        adjacent.add(mygrid.get(down));
                    }
                    mygrid.get(down).revealed = true;
                }
                if(mygrid.get(down - 1).mine != true) {
                    if(!mygrid.get(down-1).revealed && (mygrid.get(down-1).value == 0)){
                        adjacent.add(mygrid.get(down-1));
                    }
                    mygrid.get(down - 1).revealed = true;
                }
                if(mygrid.get(down + 1).mine != true) {
                    if(!mygrid.get(down+1).revealed && (mygrid.get(down+1).value == 0)){
                        adjacent.add(mygrid.get(down+1));
                    }
                    mygrid.get(down + 1).revealed = true;
                }
            }
        }
        for(int j = 0; j < adjacent.size(); j++){
            this.adj(adjacent.get(j));
        }
    }

    public int flagged(grid myGrid){
        if(myGrid.flagged){
            myGrid.flagged = false;
            myGrid.revealed = false;
        }
        else{
            myGrid.flagged = true;
            return 1;
        }
        return 0;
    }

    public int winCheck(){
        for(int i = 0; i < this.mygrid.size(); i++){
            if(mygrid.get(i).mine == true && (mygrid.get(i).flagged == false)){
                return 0;
            }
            else if(mygrid.get(i).mine == false && mygrid.get(i).revealed == false){
                return 0;
            }
        }
        return 1;
    }

    public List<grid> getMygrid() {
        return mygrid;
    }
}
