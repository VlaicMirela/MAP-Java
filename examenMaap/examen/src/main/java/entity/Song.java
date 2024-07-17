package entity;

import java.io.Serializable;

public class Song extends SongA implements Serializable {
    private String id;
    private String band;
    private String title;
    private String genre;
    private String duration;

    public Song(String id, String band, String title, String genre, String duration) {
        this.id = id;
        this.band = band;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public String getBand() {
        return band;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Piesa{" +
                "id= '" + id + '\'' +
                ", trupa= '" + band + '\'' +
                ", titlu= '" + title + '\'' +
                ", gen= '" + genre + '\'' +
                ", durata= '" + duration + '\'' +
                '}';
    }
}
