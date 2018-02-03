package com.example.praveen.floatingexample;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by praveen on 20/8/17.
 */

public class FloatService extends Service {

    WindowManager wm;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;

    }

    @Override
    public void onCreate(){
        try {
            wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            //LinearLayout ll=new LinearLayout(this);
            LinearLayout.LayoutParams llLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout fab = new LinearLayout(this);
            fab.setBackgroundColor(Color.argb(79, 255, 0, 0));
            fab.setLayoutParams(llLp);
            WindowManager.LayoutParams wmLp;
            if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                wmLp = new WindowManager.LayoutParams(100, 100, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
            }else{
                wmLp = new WindowManager.LayoutParams(100, 100, WindowManager.LayoutParams.TYPE_PHONE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
            }
            wmLp.x = 0;
            wmLp.y = 0;
            wmLp.gravity = Gravity.CENTER | Gravity.CENTER;
            wm.addView(fab, wmLp);
            fab.setOnTouchListener(new FloatListener(fab, llLp, wmLp, wm));
        }catch (Exception e){
            Log.error(e);
        }
    }

}
