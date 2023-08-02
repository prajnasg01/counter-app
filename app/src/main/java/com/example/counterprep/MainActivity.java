package com.example.counterprep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button start,stop;
    TextView cv;
    int counter=0;
    boolean r=false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cv=(TextView)findViewById(R.id.dispcv);
        
        start=(Button)findViewById(R.id.bs);
        start.setOnClickListener(this);
        
        stop=(Button)findViewById(R.id.bp);
        stop.setOnClickListener(this);
        
    }
    @Override
    public void onClick(View v) {
        if(v.equals(start))
        {
            counter=0;
            r=true;
            new MyCounter().start();

        }
        if(v.equals(stop))
        {
            r=false;
        }

    }
    Handler handler=new Handler()
    {
        public void handleMessage(Message m)
        {
            cv.setText(String.valueOf(m.what));
        }

    };

    class MyCounter extends Thread
    {
        public void run()
        {
            while(r)
            {
                counter++;
                handler.sendEmptyMessage(counter);
                try
                {
                       Thread.sleep(1000);   
                }
                catch(Exception e){}
            }
        }
    }
}
