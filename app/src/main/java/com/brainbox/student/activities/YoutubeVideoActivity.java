package com.brainbox.student.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.brainbox.student.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin-PC on 2/24/2016.
 */
public class YoutubeVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
{
	private static final int RECOVERY_REQUEST = 1;
	@Bind(R.id.youtube_view)
	YouTubePlayerView youTubeView;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube_video);
		ButterKnife.bind(this);
		youTubeView.initialize("AIzaSyAE9cJrVutX4_l4z3mWozI-P7bnuhmHetE", this);
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
	{
		if (!wasRestored)
		{
			player.cueVideo("uG_fku5splw"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
		}
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason)
	{
		if (errorReason.isUserRecoverableError())
		{
			errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
		} else
		{
			String error = errorReason.toString();
			Toast.makeText(this, error, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == RECOVERY_REQUEST)
		{
			// Retry initialization if user performed a recovery action
			getYouTubePlayerProvider().initialize("AIzaSyAE9cJrVutX4_l4z3mWozI-P7bnuhmHetE", this);
		}
	}

	protected YouTubePlayer.Provider getYouTubePlayerProvider()
	{
		return youTubeView;
	}
}
