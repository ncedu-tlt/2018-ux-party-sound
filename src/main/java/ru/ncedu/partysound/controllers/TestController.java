package ru.ncedu.partysound.controllers;

import com.sun.deploy.net.HttpResponse;
import netscape.javascript.JSObject;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.partysound.models.domain.TracksDAO;
import sun.net.www.http.HttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


@RestController
@RequestMapping("/api/test")
public class TestController {

    public static String http(String id) {

        RestTemplate restTemplate = new RestTemplate();
        String URL_EMPLOYEES = "https://api.jamendo.com/v3.0/tracks/?client_id=eeded1fc&format=jsonpretty&limit=5&include=musicinfo&id=" + id;
        // Send request with GET method and default Headers.
        String result = restTemplate.getForObject(URL_EMPLOYEES, String.class);

        System.out.println(result);
        return result;
    }


    @GetMapping
    public String get(@RequestParam("track_id") String trackId) {
        return "Hello world";
    }
    @PostMapping
    public String post(@RequestBody String str) {
        try {
            JSONObject JSONTrack = new JSONObject(str);
            JSONObject musicinfo = JSONTrack.getJSONObject("musicinfo");

            JSONObject tags = musicinfo.getJSONObject("tags");

            JSONArray genres = tags.getJSONArray("genres");
//В genres лежит массив жанров, а иногда жанра может и не быть
            String genre;
            try {
                genre = genres.getString(0);
            } catch (Exception e) {
                genre = "none";
            }

            TracksDAO track = new TracksDAO(
                    JSONTrack.getString("audio"),
                    JSONTrack.getString("name"),
                    JSONTrack.getString("artist_name"),
                    JSONTrack.getLong("artist_id"),
                    JSONTrack.getString("album_name"),
                    JSONTrack.getLong("album_id"),
                    genre
            );
            return "model with name: " + track.getName() + " has been created successfully. Author is " + track.getArtistName() + ". Genre is: " + track.getGenre();
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception";
        }
    }
}
