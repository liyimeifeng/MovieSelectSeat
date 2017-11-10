package com.android.ticket.test.login;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;

import org.greenrobot.greendao.query.QueryBuilder;

public class MainActivity extends Activity {
	protected boolean _active = true;
	protected int _splashTime = 2000;
	ProgressBar progressBarHorizontal;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.the_first);
		QueryBuilder.LOG_SQL = true;
		QueryBuilder.LOG_VALUES = true;
		progressBarHorizontal = (ProgressBar) this.findViewById(R.id.progressBarHorizontal);
		LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.animation_view);

		lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				finish();
				Intent i = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(i);
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		progressBarHorizontal.setMax(_splashTime);
		final Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
	                int waited = 0;
	                while(_active && (waited < _splashTime)) {
	                    sleep(100);
	                    if(_active) {
	                        waited += 100;
	                        progressBarHorizontal.setProgress(waited); 
	                    }
	                }
//	                CreatTable.creattable();
	            } catch(InterruptedException e) {
	                // do nothing
	            } finally {
	                finish();
	                Intent i = new Intent(MainActivity.this,
	            			LoginActivity.class);
	            	startActivity(i);
	            }
			}
		};
//		splashThread.start();
	}
}