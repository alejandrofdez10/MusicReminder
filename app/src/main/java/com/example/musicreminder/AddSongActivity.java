package com.example.musicreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddSongActivity extends AppCompatActivity
{

    private EditText etSongName, etArtistName, etReason, etYoutubeLink, etListenDate;
    private Button btnAddToPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        etSongName          = findViewById(R.id.etSongName);
        etArtistName        = findViewById(R.id.etArtistName);
        etReason            = findViewById(R.id.etReason);
        etYoutubeLink       = findViewById(R.id.etYoutubeLink);
        etListenDate        = findViewById(R.id.etListenDate);
        btnAddToPlaylist    = findViewById(R.id.btnAddToPlaylist);

        btnAddToPlaylist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String songName     = etSongName.getText().toString();
                String artistName   = etArtistName.getText().toString();
                String reason       = etReason.getText().toString();
                String youtubeLink  = etYoutubeLink.getText().toString();
                String listenDate   = etListenDate.getText().toString();

                Song newSong = new Song(songName, artistName, reason, youtubeLink, listenDate);

                Intent intent = new Intent();
                intent.putExtra("newSong", newSong);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}