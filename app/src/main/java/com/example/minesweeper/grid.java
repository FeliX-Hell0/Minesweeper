package com.example.minesweeper;

public class grid {
    public boolean mine = false;
    public boolean empty = true;
    public boolean revealed = false;
    public boolean flagged = false;
    public int value = 0;

    public int index = 0;

    public grid(int i){
        index = i;
    }
}

