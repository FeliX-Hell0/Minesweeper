package com.example.minesweeper;

import java.util.ArrayList;
import java.util.List;

public class mine{
    private List<grid> mygrid;

    public mine(){
        mygrid = new ArrayList<grid>();
        for(int i = 0; i < 80; i++){
            mygrid.add(new grid());
        }
    }

    public List<grid> getMygrid() {
        return mygrid;
    }
}
