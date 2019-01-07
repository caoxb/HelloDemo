package com.tarena.music.player;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicPlayerActivity extends AppCompatActivity {
    MusicService.MusicBinder musicBinder;

    ServiceConnection mServiceConnection =
            new ServiceConnection() {

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }

                @Override
                public void onServiceConnected(
                        ComponentName name,
                        IBinder service) {
                    musicBinder = (MusicService.MusicBinder) service;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        initView();

        Intent mServiceIntent =
                new Intent(
                        this,
                        MusicService.class);


        bindService(
                mServiceIntent,
                mServiceConnection,
                Context.BIND_AUTO_CREATE);

        initListener();
    }

    Button btPlayOne;
    Button btPlayTwo;

    Button btDoPlay;
    Button btDoPause;
    private void initView() {
        btDoPlay = (Button) findViewById(R.id.do_play);
        btDoPause = (Button) findViewById(R.id.do_pause);

        btPlayOne = (Button) findViewById(R.id.play_one);
        btPlayTwo = (Button) findViewById(R.id.play_two);


    }

    private void initListener() {
        btDoPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                musicBinder.doMusicPlay();
//				// 发送播放命令到Service
//				Intent mIntent = new Intent(
//						MusicPlayerActivity.this,
//						MusicService.class);
//
//				mIntent.putExtra(
//						MusicService.COMMAND_TAG,
//						MusicService.COMMAND_MUSIC_PLAY);
//
//				startService(mIntent);
            }
        });
        btDoPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                musicBinder.doMusicPause();
//				// 发送暂停命令到Service
//				Intent mIntent = new Intent(
//						MusicPlayerActivity.this,
//						MusicService.class);
//
//				mIntent.putExtra(
//						MusicService.COMMAND_TAG,
//						MusicService.COMMAND_MUSIC_PAUSE);
//
//				startService(mIntent);
            }
        });


        btPlayOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String path=Environment.getExternalStorageDirectory()+"/spk.wav";
                musicBinder.setMusicPath(path);
//				Intent mIntent = new Intent(
//						MusicPlayerActivity.this,
//						MusicService.class);
//
//				mIntent.putExtra(
//						MusicService.COMMAND_TAG,
//						MusicService.COMMAND_MUSIC_PATH);
//
//				mIntent.putExtra(
//						MusicService.MUSIC_PATH_TAG,
//						"/mnt/sdcard/CKG.mp3");
//
//				startService(mIntent);

            }
        });

        btPlayTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String path=Environment.getExternalStorageDirectory()+"/msg.mp3";
                musicBinder.setMusicPath(path);
//				Intent mIntent = new Intent(
//						MusicPlayerActivity.this,
//						MusicService.class);
//
//				mIntent.putExtra(
//						MusicService.COMMAND_TAG,
//						MusicService.COMMAND_MUSIC_PATH);
//
//				mIntent.putExtra(
//						MusicService.MUSIC_PATH_TAG,
//						"/mnt/sdcard/yc.mp3");
//
//				startService(mIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
