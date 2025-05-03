package com.example.demo.api.service;
import com.example.demo.api.entity.Music;
import java.util.List;

public interface MusicService {

    Music AddMusic(Music music);

    List<Music> getAllMusic();

    List<Music> getMusicByName(String name);

    List<Music> getMusicByAuthor(String author);

    Music DeleteMusic(Music music);

    Music EditMusic(Music music);
}
