package sematecandroidfridays.sugarorm;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayer extends AppCompatActivity {

    VideoView video;
    BroadcastReceiver oReceiver;
    IntentFilter filter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(oReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        if (ContextCompat.checkSelfPermission(VideoPlayer.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoPlayer.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
        }

        oReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                video.pause();
            }
        };

        filter = new IntentFilter("android.intent.action.PHONE_STATE");

        registerReceiver(oReceiver,filter);



        String url = "http://as8.cdn.asset.aparat.com/aparat-video/e7481a9938ecf8aece5b5b8a96adf8387146685-180p__80422.mp4";

        video = (VideoView) findViewById(R.id.video);
        video.setMediaController(new MediaController(this));
        video.setVideoURI(Uri.parse(url));
        video.start();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000){
            Toast.makeText(VideoPlayer.this,"OK. I manage Video!",Toast.LENGTH_LONG).show();
        }
    }
}
