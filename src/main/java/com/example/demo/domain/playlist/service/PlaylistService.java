package com.example.demo.domain.playlist.service;

import com.example.demo.domain.playlist.controller.form.PlaylistModifyRequestForm;
import com.example.demo.domain.playlist.controller.form.PlaylistReadResponseForm;
import com.example.demo.domain.playlist.controller.form.PlaylistRegisterRequestForm;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PlaylistService {
    long register(PlaylistRegisterRequestForm requestForm, HttpServletRequest request);

    int countPlaylist(HttpServletRequest request);

    List<PlaylistReadResponseForm> list();

    PlaylistReadResponseForm read(Long id);

    boolean modify(PlaylistModifyRequestForm requestForm);

    List<PlaylistReadResponseForm> listByLoginAccount(int page,HttpServletRequest request);

    boolean delete(Long playlistId);

    int likePlaylist(Long playlistId, HttpServletRequest request);

    int unlikePlaylist(Long playlistId, HttpServletRequest request);

    Boolean isPlaylistLiked(Long playlistId, HttpServletRequest request);

    List<PlaylistReadResponseForm> slicePlaylist(int page);

    List<PlaylistReadResponseForm> sortByLikersSlicePlaylist(int page);

    long countAllPlaylist();

    long countTotalPageByLoginAccount(HttpServletRequest request);

    List<PlaylistReadResponseForm> likedPlaylistByLoginAccount(int page, HttpServletRequest request);

    long countLikedPlaylist(HttpServletRequest request);

    long countPageLikedPlaylist(HttpServletRequest request);
}
