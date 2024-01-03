package com.example.musicreminder;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class SongDetailActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);

        if (getIntent() != null && getIntent().hasExtra("song"))
        {
            Song song = getIntent().getParcelableExtra("song");

            TextView txtSongNameDetail  = findViewById(R.id.txtSongNameDetail);
            TextView txtArtistDetail    = findViewById(R.id.txtArtistDetail);
            TextView txtReasonDetail    = findViewById(R.id.txtReasonDetail);

            txtSongNameDetail.setText(song.getName());
            txtArtistDetail.setText(song.getArtist());
            txtReasonDetail.setText(song.getReason());
        }
    }
}