package com.example.musicreminder;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder>
{

    private List<Song> songList;

    public SongListAdapter(List<Song> songList)
    {
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Song song = songList.get(position);

        holder.btnYoutubeLink.setImageResource(R.drawable.ic_youtube_thumbnail_placeholder);
        holder.txtSongName.setText(song.getName());
        holder.txtArtist.setText(song.getArtist());
        holder.txtListenDate.setText(song.getListenDate());
        holder.txtReason.setText(song.getReason());

        holder.btnYoutubeLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openYoutubeLink(song.getYoutubeLink(), v);
            }

            private void openYoutubeLink(String youtubeLink, View view)
            {
                if (!youtubeLink.isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));

                    if (intent.resolveActivity(view.getContext().getPackageManager()) != null)
                    {
                        view.getContext().startActivity(intent);
                    }
                    else
                    {
                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                        view.getContext().startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return songList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        AppCompatImageButton btnYoutubeLink;
        TextView txtSongName;
        TextView txtArtist;
        TextView txtListenDate;
        TextView txtReason;

        ViewHolder(View itemView)
        {
            super(itemView);
            btnYoutubeLink      = itemView.findViewById(R.id.btnYoutubeLink);
            txtSongName         = itemView.findViewById(R.id.txtSongName);
            txtArtist           = itemView.findViewById(R.id.txtArtist);
            txtListenDate       = itemView.findViewById(R.id.txtListenDate);
            txtReason           = itemView.findViewById(R.id.txtReason);
        }
    }
}
