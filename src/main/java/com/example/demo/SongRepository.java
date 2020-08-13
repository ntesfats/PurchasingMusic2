package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {

    Iterable<Song> findSongBysongGenre(String genreName);

    Iterable<Song> getAllByOrderBySongTitle();
}
