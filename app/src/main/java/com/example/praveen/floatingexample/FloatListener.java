package com.example.praveen.floatingexample;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.view.MotionEvent.*;

/**
 * Created by praveen on 20/8/17.
 */

public class FloatListener implements View.OnTouchListener {

    LinearLayout fab;
    DisplayMetrics dm;
    WindowManager.LayoutParams updateParams;
    LinearLayout.LayoutParams ll;
    WindowManager wm;

    int x,y;
    float touchX,touchY;

    public FloatListener( final LinearLayout fab, LinearLayout.LayoutParams ll, WindowManager.LayoutParams updateParam,WindowManager wm){
        this.fab=fab;
        this.updateParams=updateParam;
        this.ll=ll;
        this.wm=wm;
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.println("Pressed");

                Toast.makeText(fab.getContext().getApplicationContext(),"Clicked",Toast.LENGTH_LONG);
            }
        });
       // wm.getDefaultDisplay().getRealMetrics(dm);

    }




    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case ACTION_DOWN:
                x=updateParams.x;
                y=updateParams.y;
                touchX=motionEvent.getRawX();
                touchY=motionEvent.getRawY();
                break;
            case ACTION_MOVE:
                try {
                    updateParams.x = (int) ( x +  (motionEvent.getRawX() -touchX ));
                    updateParams.y = (int) ( y +  (motionEvent.getRawY() -touchY )) ;
                    wm.updateViewLayout(fab, updateParams);
                }catch(Exception e){
                    Log.error( e);
                }
                break;
            default:
                break;
        }

        return false;
    }


}
