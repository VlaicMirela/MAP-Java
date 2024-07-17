package RepositoryTxt;

import entity.Song;
import repository.AbstractRepo;
import service.Service;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class SongRepoTxt extends AbstractRepo<Song> {
    protected String filePath;
    protected Service<Song> service;

    public SongRepoTxt(String filePath, Service<Song> service) throws IOException {
        this.filePath = filePath;
        this.service = service;
    }
    public void saveData() throws IOException, SQLException {
        List<Song> songs= (List<Song>) service.getAll();
        List<Song> selectedSongs = new ArrayList<>();
        Random random = new Random();
        int currentDuration = 0;

        while (currentDuration < 15 && !songs.isEmpty()) {
            int index = random.nextInt(songs.size());
            Song selectedSong = songs.get(index);

            String[] durationParts = selectedSong.getDuration().split(":");
            int minutes = Integer.parseInt(durationParts[0]);
            int seconds = Integer.parseInt(durationParts[1]);

            if (currentDuration + minutes <= 15) {
                selectedSongs.add(selectedSong);
                currentDuration += minutes;
            }

            songs.remove(index);
        }

        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (Song item : selectedSongs) {
                String line = item.getId() + "," + item.getBand() + "," + item.getTitle() + "," + item.getGenre() + "," + item.getDuration();
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void add(Song item) throws IOException {
        super.add(item);
    }
}
