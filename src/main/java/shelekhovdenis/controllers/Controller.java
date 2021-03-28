package shelekhovdenis.controllers;

import com.owlike.genson.Genson;
import org.apache.log4j.Logger;
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

/**
 * Controller with a set of methods for creating queries and getting results
 */
@RestController
@RequestMapping(path = "/api")
public class Controller {

    Logger logger = Logger.getLogger(Controller.class);
    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
    public static final String IOEXCEPTION = "file is missing ";
    public static final String EMPTY_REQUEST = "the request is empty";
    public static final String EMPTY_RESPONSE = "the response is empty";

    /**
     * Gets an album by title and artist name
     * @param name album title
     * @param artist artist name
     * @return album as string
     */
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
            logger.error(IOEXCEPTION + "to path "+ PATH_TO_PROPERTIES, e);
        }


        LastFmService album = getLastFmService(restTemplate, genson, getRequest);

        System.out.println(album.getAlbumToString());

        DOCBuilder doc = new DOCBuilder();
        try {
            doc.writeToFile(album.getAlbum());
        } catch (IOException e) {
            logger.error(IOEXCEPTION, e);
        }

        return album.getAlbumToString();

    }

    /**
     * behind the track title and artist name defines the album name then calls the method "getAlbumUsingNameAndArtist" and returns the resulting album
     * @param artist artist name
     * @param track name of the track
     * @return album as string
     */
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
            logger.error(IOEXCEPTION + "to path "+ PATH_TO_PROPERTIES, e);
        }

        LastFmService AlbumName = getLastFmService(restTemplate, genson, getRequest);

        if(AlbumName.getAlbumNameUsingTrackAndArtistToString() == null){
            return "not an album";
        }

        return getAlbumUsingNameAndArtist(AlbumName.getAlbumNameUsingTrackAndArtistToString(), artist);

    }

    /**
     * using the track name, determines the list of artists with such a track, after which it calls the method "getAlbumUsingTrackAndArtist" and compiles a list of the resulting albums
     * @param track name of the track
     * @return list of albums as a string
     */
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
            logger.error(IOEXCEPTION + "to path "+ PATH_TO_PROPERTIES, e);
        }

        LastFmService list = getLastFmService(restTemplate, genson, getRequest);

        ArrayList<String> artistList = list.getArtistUsingTrack();
        StringBuilder strbuild = new StringBuilder();

        DOCBuilder doc = new DOCBuilder();
        try {
            doc.ClearFile();
        } catch (IOException e) {
            logger.error(IOEXCEPTION, e);
        }

        for(String artist : artistList){
            String album = getAlbumUsingTrackAndArtist(artist,track);
            if(artist.contains("&") || artist.contains("#") || album.equals("not an album")){
                continue;
            }

            strbuild.append(album);
        }

        return strbuild.toString();

    }

    /**
     * compiles a list of album names by the artist's name, then calls the method "getAlbumUsingNameAndArtist" and makes a list of the resulting albums
     * @param artist artist name
     * @return list of albums as a string
     */
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
            logger.error(IOEXCEPTION + "to path "+ PATH_TO_PROPERTIES, e);
        }

        LastFmService lastFmService = getLastFmService(restTemplate, genson, getRequest);

        String[] str = lastFmService.getAlbumsNameUsingArtistToString().split(", ");
        StringBuilder strbuild = new StringBuilder();

        DOCBuilder doc = new DOCBuilder();
        try {
            doc.ClearFile();
        } catch (IOException e) {
            logger.error(IOEXCEPTION, e);
        }

        for(String temp : str){
            if(temp.contains("&") || temp.contains("#")){
                continue;
            }
            strbuild.append(getAlbumUsingNameAndArtist(temp, artist));
        }

        return strbuild.toString();

    }

    /**
     * creates an object of the class LastFmService
     * @param restTemplate the central Spring class for client-side HTTP access
     * @param genson class for working with type json
     * @param getRequest request to send to LastFm
     * @return object of the class LastFmService
     */
    private LastFmService getLastFmService(RestTemplate restTemplate, Genson genson, String getRequest) {
        if(getRequest == null){
            logger.error(EMPTY_REQUEST);
            throw new NullPointerException();
        }

        ResponseEntity<String> response = restTemplate.exchange(getRequest, HttpMethod.GET, null, String.class);

        if(response.getBody() == null){
            logger.error(EMPTY_RESPONSE);
            throw new NullPointerException();
        }

        return genson.deserialize(response.getBody(), LastFmService.class);
    }

}
