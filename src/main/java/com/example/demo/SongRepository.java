package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface SongRepository extends CrudRepository<Song, Long> {

    Iterable<Song> findSongBysongGenre(String genreName);

    Iterable<Song> getAllByOrderBySongTitle();

    Collection<Song> findAllBySalesOrderBySongTitle(Sale sale);
}
