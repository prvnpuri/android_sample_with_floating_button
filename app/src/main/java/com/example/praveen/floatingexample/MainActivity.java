package com.example.praveen.floatingexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {


    public MainActivity(){
        this.mainActivity=this;
    }

    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(mainActivity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 5469);
            }

        }
        if(Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{android.Manifest.permission.SYSTEM_ALERT_WINDOW}, 1234);
        }
        setContentView(R.layout.activity_main);

        final Context cntext=getApplicationContext();

        final EditText et=(EditText) findViewById(R.id.outputpanel);
        OutputStream sysOutputStream=new OutputStream() {
            @Override
            public void write(int i) throws IOException {
                et.append( ""+(char)i );
            }
        };
        System.setOut(new PrintStream(sysOutputStream));
        System.setErr(new PrintStream(sysOutputStream));
        ((Button)findViewById(R.id.activate_float)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(cntext,"Stating..",Toast.LENGTH_LONG).show();

                try {
                    startService(new Intent(MainActivity.this, FloatService.class));
                }catch (Exception e){
                    Log.error(e);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
