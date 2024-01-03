package com.example.musicreminder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class SongListActivity extends AppCompatActivity
{

    private List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        if (getIntent() != null && getIntent().hasExtra("songList"))
        {
            songList = getIntent().getParcelableArrayListExtra("songList");
        }
        else
        {
            songList = loadSongList();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SongListAdapter adapter = new SongListAdapter(songList);
        recyclerView.setAdapter(adapter);
    }

    private List<Song> loadSongList()
    {
        SharedPreferences preferences   = getSharedPreferences("SONG_PREFERENCES", MODE_PRIVATE);
        String songListJson             = preferences.getString("songList", null);
        Type type                       = new TypeToken<List<Song>>(){}.getType();

        return new Gson().fromJson(songListJson, type);
    }

    public void openYoutubeLink(String youtubeLink)
    {
        if (!youtubeLink.isEmpty())
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));

            if (intent.resolveActivity(getPackageManager()) != null)
            {
                startActivity(intent);
            }
            else
            {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                startActivity(intent);
            }
        }
    }
}


