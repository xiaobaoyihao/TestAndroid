package com.dingbaosheng;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class AudioRecordTest extends Activity {

	private static final String LOG_TAG = "AudioRecordTest";
	private static String mFileName = null;
	// 录音按钮
	private RecordButton mRecordButton = null;
	private MediaRecorder mRecorder = null;

	// 回放按钮
	private PlayButton mPlayButton = null;
	private MediaPlayer mPlayer = null;

	private String audioDir = Environment.getExternalStorageDirectory()+"/pingan_audio";
	
	// 当录音按钮被click时调用此方法，开始或停止录音
	private void onRecord(boolean start) {
		if (start) {
			startRecording();
		} else {
			stopRecording();
		}
	}

	// 当播放按钮被click时调用此方法，开始或停止播放
	private void onPlay(boolean start) {
		if (start) {
			startPlaying();
		} else {
			stopPlaying();
		}
	}

	private void startPlaying() {
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setType("audio/amr");
//		intent.setData(Uri.fromFile(new File(mFileName)));
		
		intent.setDataAndType(Uri.fromFile(new File(mFileName)), "audio/amr");
		startActivity(intent);
		/*mPlayer = new MediaPlayer();
		try {
			// 设置要播放的文件
			mPlayer.setDataSource(mFileName);
			mPlayer.prepare();
			// 播放之
			mPlayer.start();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}*/
	}

	// 停止播放
	private void stopPlaying() {
//		mPlayer.release();
//		mPlayer = null;
	}

	private void startRecording() {
		mRecorder = new MediaRecorder();
		// 设置音源为Micphone
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		// 设置封装格式
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
		mFileName = audioDir+"/"+format.format(new Date())+".amr";
		
		mRecorder.setOutputFile(mFileName);
		// 设置编码格式
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

		try {
			mRecorder.prepare();
		} catch (IOException e) {
			Log.e(LOG_TAG, "prepare() failed");
		}

		mRecorder.start();
	}

	private void stopRecording() {
		mRecorder.stop();
		mRecorder.release();
		mRecorder = null;
	}

	// 定义录音按钮
	class RecordButton extends Button {
		boolean mStartRecording = true;

		OnClickListener clicker = new OnClickListener() {
			@Override
			public void onClick(View v) {
				onRecord(mStartRecording);
				if (mStartRecording) {
					setText("Stop recording");
				} else {
					setText("Start recording");
				}
				mStartRecording = !mStartRecording;
			}
		};

		public RecordButton(Context ctx) {
			super(ctx);
			setText("Start recording");
			setOnClickListener(clicker);
		}
	}

	// 定义播放按钮
	class PlayButton extends Button {
		boolean mStartPlaying = true;

		OnClickListener clicker = new OnClickListener() {
			@Override
			public void onClick(View v) {
				onPlay(mStartPlaying);
				if (mStartPlaying) {
					setText("Stop playing");
				} else {
					setText("Start playing");
				}
				mStartPlaying = !mStartPlaying;
			}
		};

		public PlayButton(Context ctx) {
			super(ctx);
			setText("Start playing");
			setOnClickListener(clicker);
		}
	}

	// 构造方法
	public AudioRecordTest() {
//		mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//		mFileName += "/audiorecordtest.3gp";
		File file = new File(audioDir);
		if(!file.exists()){
			file.mkdirs();
		}
	}

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// 构造界面
		LinearLayout ll = new LinearLayout(this);
		mRecordButton = new RecordButton(this);
		ll.addView(mRecordButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		mPlayButton = new PlayButton(this);
		ll.addView(mPlayButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0));
		setContentView(ll);
	}

	@Override
	public void onPause() {
		super.onPause();
		// Activity暂停时释放录音和播放对象
		if (mRecorder != null) {
			mRecorder.release();
			mRecorder = null;
		}

		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
