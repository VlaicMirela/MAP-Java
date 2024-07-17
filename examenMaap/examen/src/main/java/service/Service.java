package service;

import entity.Song;
import repository.InterfaceRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class Service<T extends Song> {
    private InterfaceRepo<T> repo;

    public Service(InterfaceRepo<T> repo) {
        this.repo = repo;
    }

    public void add(T item) throws IOException, SQLException {
        this.repo.add(item);
    }
    public Collection<T> getAll() throws SQLException {
        return this.repo.getAll();
    }
}