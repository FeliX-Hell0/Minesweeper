package com.example.minesweeper;

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
    }

    public List<grid> getMygrid() {
        return mygrid;
    }
}
