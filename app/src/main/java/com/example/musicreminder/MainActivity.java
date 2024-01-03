package com.example.musicreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private List<Song> songList;
    private static final int ADD_SONG_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadSongList();

        Button btnAddSong = findViewById(R.id.btnAddSong);
        btnAddSong.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent addSongIntent = new Intent(MainActivity.this, AddSongActivity.class);
                startActivityForResult(addSongIntent, ADD_SONG_REQUEST_CODE);
            }
        });

        Button btnViewList = findViewById(R.id.btnViewList);
        btnViewList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent viewListIntent = new Intent(MainActivity.this, SongListActivity.class);
                viewListIntent.putParcelableArrayListExtra("songList", (ArrayList<Song>) songList);
                startActivity(viewListIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_SONG_REQUEST_CODE && resultCode == RESULT_OK && data != null)
        {
            Song newSong = data.getParcelableExtra("newSong");
            if (newSong != null)
            {
                songList.add(newSong);
                saveSongList();
            }
        }
    }

    private void loadSongList()
    {
        SharedPreferences preferences = getSharedPreferences("SONG_PREFERENCES", MODE_PRIVATE);
        String songListJson = preferences.getString("songList", null);

        Type type   = new TypeToken<List<Song>>(){}.getType();
        songList    = new Gson().fromJson(songListJson, type);

        if (songList == null)
        {
            songList = new ArrayList<>();
        }
    }

    private void saveSongList()
    {
        SharedPreferences.Editor editor = getSharedPreferences("SONG_PREFERENCES", MODE_PRIVATE).edit();
        editor.putString("songList", new Gson().toJson(songList));
        editor.apply();
    }
}


