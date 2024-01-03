package com.example.musicreminder;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable
{
    private String name;
    private String artist;
    private String reason;
    private String youtubeLink;
    private String listenDate;

    public Song(String name, String artist, String reason, String youtubeLink, String listenDate)
    {
        this.name           = name;
        this.artist         = artist;
        this.reason         = reason;
        this.youtubeLink    = youtubeLink;
        this.listenDate     = listenDate;
    }

    public String getName()
    {
        return name;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getReason()
    {
        return reason;
    }

    public String getYoutubeLink()
    {
        return youtubeLink;
    }

    public String getListenDate()
    {
        return listenDate;
    }

    protected Song(Parcel in)
    {
        name        = in.readString();
        artist      = in.readString();
        reason      = in.readString();
        youtubeLink = in.readString();
        listenDate  = in.readString();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeString(reason);
        dest.writeString(youtubeLink);
        dest.writeString(listenDate);
    }

    public static final Creator<Song> CREATOR = new Creator<Song>()
    {
        @Override
        public Song createFromParcel(Parcel in)
        {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size)
        {
            return new Song[size];
        }
    };
}

