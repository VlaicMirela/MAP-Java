package repositorySQL;


import entity.Song;
import repository.InterfaceRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepoSQL implements InterfaceRepo<Song> {
    private static final String INSERT = "INSERT INTO songs(id, band, title, genre, duration) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM songs ORDER BY LOWER(band), duration";
    private static final String SELECT_BY_ID = "SELECT * FROM songs WHERE uniqueID = ?";
    private Connection conn;

    public SongRepoSQL(Connection connection) {
        this.conn = connection;
    }

    public void add(Song Song) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(INSERT)) {
            statement.setString(1, Song.getId());
            statement.setString(2, Song.getBand());
            statement.setString(3, Song.getTitle());
            statement.setString(4, Song.getGenre());
            statement.setString(5, Song.getDuration());
            statement.executeUpdate();
        }
    }

    public List<Song> getAll() throws SQLException {
        List<Song> songs = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Song Song = ToSong(resultSet);
                songs.add(Song);
            }
        }
        return songs;
    }

    public Song getById(String uniqueID) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID)) {
            statement.setString(1, uniqueID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return ToSong(resultSet);
                }
            }
        }
        return null;
    }


    private Song ToSong(ResultSet resultSet) throws SQLException {
        String uniqueID = resultSet.getString("id");
        String band = resultSet.getString("band");
        String title = resultSet.getString("title");
        String genre = resultSet.getString("genre");
        String duration = resultSet.getString("duration");
        return new Song(uniqueID, band, title, genre,duration);
    }
}
