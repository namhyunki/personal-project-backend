package com.example.demo.domain.playlist.service;

import com.example.demo.domain.playlist.controller.form.PlaylistReadResponseForm;
import com.example.demo.domain.playlist.controller.form.PlaylistRegisterRequestForm;
import com.example.demo.domain.playlist.entity.Playlist;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PlaylistService {
    Playlist register(PlaylistRegisterRequestForm requestForm, HttpServletRequest request);

    int countPlaylist(HttpServletRequest request);

    List<Playlist> list();

    PlaylistReadResponseForm read(Long id);
}