package com.example.demo.domain.playlist.service;

import com.example.demo.authentication.jwt.JwtTokenUtil;
import com.example.demo.domain.account.entity.Account;
import com.example.demo.domain.account.repository.AccountRepository;
import com.example.demo.domain.playlist.controller.form.PlaylistReadResponseForm;
import com.example.demo.domain.playlist.controller.form.PlaylistRegisterRequestForm;
import com.example.demo.domain.playlist.entity.Playlist;
import com.example.demo.domain.playlist.repository.PlaylistRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService{

    @Value("${jwt.secret}")
    private String secretKey;

    final private PlaylistRepository playlistRepository;

    final private AccountRepository accountRepository;
    @Override
    public Playlist register(PlaylistRegisterRequestForm requestForm, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String email = null;

        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("AccessToken")) {
                String token = cookie.getValue();
                email = JwtTokenUtil.getEmail(token, secretKey);
                break;
            }
        }

        Account account = accountRepository.findWithPlaylistByEmail(email);

        final Playlist playlist = new Playlist(requestForm.getTitle(), account);

        return playlistRepository.save(playlist);
    }

    @Override
    public int countPlaylist(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String email = null;

        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("AccessToken")) {
                String token = cookie.getValue();
                email = JwtTokenUtil.getEmail(token, secretKey);
                break;
            }
        }

        int count = playlistRepository.countPlaylistByAccountId(accountRepository.findByEmail(email).get().getId());
        log.info("playlist count = " + count );
        return count;
    }

    @Override
    public List<Playlist> list() {
        return playlistRepository.findAll();
    }

    @Override
    public PlaylistReadResponseForm read(Long id) {
        Playlist playlist = playlistRepository.findWithSongById(id);

        log.info(String.valueOf(playlist.getSongList()));
        return new PlaylistReadResponseForm(playlist, playlist.getSongList());
    }

//    @Transactional
//    public List<FilePaths> getFilePathList(Long boardId) {
//        List<FilePaths> savedFilePath = boardRepository.findById(boardId).get().getFilePathList();
//        List<Long> idList = savedFilePath.stream().map(FilePaths::getFileId).toList();
//        return savedFilePath;
//    }
}