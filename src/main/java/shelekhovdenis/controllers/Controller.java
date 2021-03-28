package shelekhovdenis.controllers;

import com.owlike.genson.Genson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import shelekhovdenis.services.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";

    @RequestMapping(path = "/test")
    public ResponseEntity<?> testMethod(){
        System.out.println("ok");
        return ResponseEntity.ok("ok");
    }

    @RequestMapping(path = "/getAlbum")
    public String getAlbumUsingNameAndArtist(@RequestParam(value = "name", defaultValue = "Believe") String name, @RequestParam(value = "artist", defaultValue = "Cher") String artist) {
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        RestTemplate restTemplate = new RestTemplate();
        Genson genson = new Genson();
        String getRequest = null;


        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            String API_URL = prop.getProperty("API_URL");
            String API_KEY = prop.getProperty("API_KEY");
            String REQUEST_URL = prop.getProperty("GET_ALBUM_USING_NAME_AND_ARTIST");
            getRequest = API_URL + REQUEST_URL + "&api_key=" + API_KEY + "&artist=" + artist + "&album=" + name + "&format=json";

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(getRequest, HttpMethod.GET, null, String.class);

        LastFmService album = genson.deserialize(response.getBody(), LastFmService.class);
        System.out.println(album.getAlbumToString());

        DOCBuilder doc = new DOCBuilder();
        try {
            doc.writeToFile(album.getAlbum());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return album.getAlbumToString();

    }

    @RequestMapping(path = "/getAlbum/UsingTrackAndArtist")
    public String getAlbumUsingTrackAndArtist(@RequestParam(value = "artist", defaultValue = "Cher") String artist, @RequestParam(value = "track", defaultValue = "Believe") String track){

        FileInputStream fileInputStream;
        Properties prop = new Properties();
        RestTemplate restTemplate = new RestTemplate();
        Genson genson = new Genson();
        String getRequest = null;

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            String API_URL = prop.getProperty("API_URL");
            String API_KEY = prop.getProperty("API_KEY");
            String REQUEST_URL = prop.getProperty("GET_TRACK_USING_TITLE_AND_ARTIST");
            getRequest = API_URL + REQUEST_URL + "&api_key=" + API_KEY + "&artist=" + artist + "&track=" + track + "&format=json";

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(getRequest, HttpMethod.GET, null, String.class);

        LastFmService AlbumName = genson.deserialize(response.getBody(), LastFmService.class);

        if(AlbumName.getAlbumNameUsingTrackAndArtistToString() == null){
            return "not an album";
        }

        return getAlbumUsingNameAndArtist(AlbumName.getAlbumNameUsingTrackAndArtistToString(), artist);

    }

    @RequestMapping(path = "/getAlbum/UsingTrack")
    public String getAlbumsUsingTrack(@RequestParam(value = "track", defaultValue = "Believe") String track){

        FileInputStream fileInputStream;
        Properties prop = new Properties();
        RestTemplate restTemplate = new RestTemplate();
        Genson genson = new Genson();
        String getRequest = null;

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            String API_URL = prop.getProperty("API_URL");
            String API_KEY = prop.getProperty("API_KEY");
            String REQUEST_URL = prop.getProperty("GET_ARTISTS_USING_TITLE");
            getRequest = API_URL + REQUEST_URL + "&track=" + track + "&api_key=" + API_KEY  + "&format=json";

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(getRequest, HttpMethod.GET, null, String.class);

        LastFmService list = genson.deserialize(response.getBody(), LastFmService.class);

        ArrayList<String> artistList = list.getArtistUsingTrack();
        StringBuilder strbuild = new StringBuilder();

        DOCBuilder doc = new DOCBuilder();
        doc.ClearFile();

        for(String artist : artistList){
            String album = getAlbumUsingTrackAndArtist(artist,track);
            if(artist.contains("&") || artist.contains("#") || album.equals("not an album")){
                continue;
            }

            strbuild.append(album);
        }

        return strbuild.toString();

    }

    @RequestMapping(path = "/getAlbum/UsingArtist")
    public String getAlbumsUsingArtists(@RequestParam(value = "artist", defaultValue = "Cher") String artist){

        FileInputStream fileInputStream;
        Properties prop = new Properties();
        RestTemplate restTemplate = new RestTemplate();
        Genson genson = new Genson();
        String getRequest = null;

        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

            String API_URL = prop.getProperty("API_URL");
            String API_KEY = prop.getProperty("API_KEY");
            String REQUEST_URL = prop.getProperty("GET_ALBUMS_USING_ARTIST");
            getRequest = API_URL + REQUEST_URL + "&artist=" + artist + "&api_key=" + API_KEY + "&format=json";

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }

        ResponseEntity<String> response = restTemplate.exchange(getRequest, HttpMethod.GET, null, String.class);

        LastFmService lastFmService = genson.deserialize(response.getBody(), LastFmService.class);

        String[] str = lastFmService.getAlbumsNameUsingArtistToString().split(", ");
        StringBuilder strbuild = new StringBuilder();

        DOCBuilder doc = new DOCBuilder();
        doc.ClearFile();

        for(String temp : str){
            if(temp.contains("&") || temp.contains("#")){
                continue;
            }

            strbuild.append(getAlbumUsingNameAndArtist(temp, artist));
        }

        return strbuild.toString();

    }

}
