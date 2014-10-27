package com.example.rasmus.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                for (int i = 0; i < 15; i++)
                    am.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                Toast.makeText(getBaseContext(), "Sample Text", Toast.LENGTH_SHORT).show();
                try {
                    MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.turn);
                    mp.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new Bckgrnd().execute(0);
            }
        });
    }

    private class Bckgrnd extends AsyncTask<Integer, Integer, Integer> {
        protected Integer doInBackground(Integer... what) {
            final int[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA};
            final Random r = new Random();
            for (int i = 0; i < 210; i++) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyActivity.this.setActivityBackgroundcolor(colors[r.nextInt(colors.length - 1)]);
                    }
                });
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyActivity.this.setActivityBackgroundcolor(Color.WHITE);
                }
            });
            return 0;
        }
    }

    public void setActivityBackgroundcolor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
