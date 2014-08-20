package com.dingbaosheng;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ProgressSeekBarRecord extends Activity implements OnClickListener {

	SeekBar seek_bar;
	Button play_button, pause_button;
	MediaPlayer player;
	TextView text_shown;
	Handler seekHandler = new Handler();

	public static final String DIR_RECORD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator+"鼎盛通" + File.separator + "record";
	
	File file1;
	File file2;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.record_progress);
		file1 = new File(DIR_RECORD, "1.mp3");
		file2 = new File(DIR_RECORD, "2.mp3");
		
		getInit();
		seekUpdation();
	}

	public void getInit() {
		seek_bar = (SeekBar) findViewById(R.id.seek_bar);
		play_button = (Button) findViewById(R.id.play_button);
		pause_button = (Button) findViewById(R.id.pause_button);
		text_shown = (TextView) findViewById(R.id.text_shown);
		play_button.setOnClickListener(this);
		pause_button.setOnClickListener(this);
		player = MediaPlayer.create(this, Uri.fromFile(file1));
		seek_bar.setMax(player.getDuration());

	}

	Runnable run = new Runnable() {

		@Override
		public void run() {
			Log.e("run", "=====================seekUpdation");
			seekUpdation();
		}
	};

	public void seekUpdation() {

		seek_bar.setProgress(player.getCurrentPosition());
		seekHandler.postDelayed(run, 1000);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.play_button:
			text_shown.setText("Playing...");
			player.start();
			break;
		case R.id.pause_button:
			player.pause();
			text_shown.setText("Paused...");
		}

	}
}
