package com.example.android.musicaudiotest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // initialize the variable
    private Button mPlay, mPause;
    private MediaPlayer mMediaPlayer;
    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed
     * playing the audio file. (call the subclass OnCompletionListener, create a new object
     * and create a method (@link releaseMediaPlayer from the interface
     */

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the views ID
        mPlay = findViewById(R.id.play);
        mPause = findViewById(R.id.pause);

        // declare OnclickListener on the button play and its interface
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Release the media player if it currently exists because we are about to
                // // play a different sound file
                releaseMediaPlayer();
                Toast.makeText(getApplicationContext(), "playing song", Toast.LENGTH_SHORT).show();

                // Instantiate the object and start the audio file
                mMediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.poltergeist);
                mMediaPlayer.start();
                // Setup a listener on the media player, so that we can stop and release the
                //media player once the sound has finished playing.
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        mPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "pause song", Toast.LENGTH_SHORT).show();
                mMediaPlayer.pause();
            }
        });
    }

    /**
     * Clean up the media player by releasing its resources.
     */

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
