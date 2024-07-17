package repository;
import entity.SongA;
import entity.Song;

import java.io.IOException;
import java.util.*;

public class AbstractRepo<T extends Song> implements InterfaceRepo<T> {

    protected List<T> data = new ArrayList<>();

    @Override
    public void add(T item) throws IOException {
        int index = Collections.binarySearch(data, item, (a, b) -> {
            int comptrupa = a.getBand().compareTo(b.getBand());
            if (comptrupa != 0) {
                return comptrupa;
            } else {
                return a.getDuration().compareTo(b.getDuration());
            }
        });

        if (index < 0) {
            index = Math.abs(index) - 1;
        }

        data.add(index, item);
    }

    public boolean existsByID(String id) {
        for (T item : data) {
            if (item instanceof SongA entity) {
                if (entity.getId().equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Collection<T> getAll() {
        return new ArrayList<T>(data);
    }

}