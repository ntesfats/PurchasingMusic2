package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    Iterable<Song> findSongBysongGenre(String genreName);

    List<Song> findAll();

    Iterable<Song> getAllByOrderBySongTitle();

    Collection<Song> findAllBySalesOrderBySongTitle(Sale sale);
}
