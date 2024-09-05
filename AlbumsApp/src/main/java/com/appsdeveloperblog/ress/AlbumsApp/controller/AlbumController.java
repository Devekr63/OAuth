package com.appsdeveloperblog.ress.AlbumsApp.controller;

import com.appsdeveloperblog.ress.AlbumsApp.data.Album;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AlbumController {

    @GetMapping("/albums")
    public List<Album> userAlbums(){
        return Arrays.asList(
            new Album("1001","Heaven And Hell", "9998","Heavy Metal", "some url"),
            new Album("1011","Man is Monster", "9997","Heavy Metal", "some url")
        );
    }
}
