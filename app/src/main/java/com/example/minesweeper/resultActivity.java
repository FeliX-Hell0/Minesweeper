package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class resultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView text = findViewById(R.id.button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"restart", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(resultActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra("Message");
        String result;
        if(message.equals("You Lost!")){
            result = "You Lost!\n";
        }
        else{result = "Used " + message + " seconds.\nYou won!\nGood Job!\n";}

        TextView textView = findViewById(R.id.result_time);
        textView.setText(result);
    }

}