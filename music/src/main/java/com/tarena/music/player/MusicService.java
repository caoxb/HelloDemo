package com.tarena.music.player;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {

	MediaPlayer mMediaPlayer;

	public static final String COMMAND_MUSIC_PATH = "COMMAND_MUSIC_PATH";
	public static final String COMMAND_MUSIC_PLAY = "COMMAND_MUSIC_PLAY";
	public static final String COMMAND_MUSIC_PAUSE = "COMMAND_MUSIC_PAUSE";

	public static final String COMMAND_TAG = "COMMAND_TAG";
	public static final String MUSIC_PATH_TAG = "MUSIC_PATH_TAG";

	MusicBinder mMusicBinder;
	@Override
	public IBinder onBind(Intent intent) {
		if(null == mMusicBinder){
			mMusicBinder = new MusicBinder();
		}
		return mMusicBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mMediaPlayer = new MediaPlayer();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

//		//得到通过Intent传递过来的Music路径
//		String strMusicPath = intent.getCharSequenceExtra("MusicPath").toString();
//		//调用音乐播放的方法
//		doPlayMusic(strMusicPath);

//		//首先判断是什么命令，根据命令调用相关方法
//		String strCommand =
//				intent.getCharSequenceExtra(
//						COMMAND_TAG).toString();
//
//		if(COMMAND_MUSIC_PATH.equals(strCommand)){
//			//得到音乐的路径
//			String strMusicPath =
//					intent.getCharSequenceExtra(
//							MUSIC_PATH_TAG).toString();
//			//调用设置音乐文件路径的方法
//			setPlayPath(strMusicPath);
//		}else if(COMMAND_MUSIC_PLAY.equals(strCommand)){
//			//调用播放音乐的方法
//			doPlay();
//		}else if(COMMAND_MUSIC_PAUSE.equals(strCommand)){
//			//调用暂停音乐的方法
//			doPause();
//		}

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	private void doPlay(){
		mMediaPlayer.start();
	}
	private void doPause(){
		mMediaPlayer.pause();
	}
	private void setPlayPath(String strPlayPath){
		try {
			mMediaPlayer.reset();

			mMediaPlayer.setDataSource(strPlayPath);

			mMediaPlayer.prepare();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doPlayMusic(String strMusicPath) {
		try {
			mMediaPlayer.reset();

			mMediaPlayer.setDataSource(strMusicPath);

			mMediaPlayer.prepare();

			mMediaPlayer.start();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class MusicBinder extends Binder{
		//设置播放的文件
		public void setMusicPath(String strMusicPath){
			setPlayPath(strMusicPath);
		}
		//播放音乐
		public void doMusicPlay(){
			doPlay();
		}
		//暂停音乐
		public void doMusicPause(){
			doPause();
		}

	}

}
