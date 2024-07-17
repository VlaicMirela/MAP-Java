package repository;

import entity.SongA;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public interface InterfaceRepo <T extends SongA>{
    void add(T item) throws IOException, SQLException;

    Collection<T> getAll() throws SQLException;
}
