package shelekhovdenis.services;

import shelekhovdenis.models.album.AlbumObject;
import shelekhovdenis.models.artists.AlbumsListObject;
import shelekhovdenis.models.track.TrackmatchesObject;
import shelekhovdenis.models.trackandartist.AlbumNameObject;

import java.util.ArrayList;

/**
 * a class that parses a request for objects
 */
public class LastFmService {

    AlbumNameObject albumNameObject;
    AlbumObject albumObject;
    AlbumsListObject topAlbums;
    TrackmatchesObject results;

    /**
     * parameterless constructor
     */
    public LastFmService() {
    }

    /**
     * object constructor "AlbumNameObject"
     * @param albumNameObject object to pars the responsive "track.getInfo"
     */
    public LastFmService(AlbumNameObject albumNameObject) {
        this.albumNameObject = albumNameObject;
    }

    /**
     * object constructor "AlbumObject"
     * @param albumObject object to pars the responsive "album.getinfo"
     */
    public LastFmService(AlbumObject albumObject) {
        this.albumObject = albumObject;
    }

    /**
     * object constructor object to pars the responsive "AlbumsListObject"
     * @param topAlbums
     */
    public LastFmService(AlbumsListObject topAlbums) {
        this.topAlbums = topAlbums;
    }

    /**
     * object constructor "TrackmatchesObject"
     * @param results object to pars the responsive "track.search"
     */
    public LastFmService(TrackmatchesObject results) {
        this.results = results;
    }

    public AlbumNameObject getTrack() {
        return albumNameObject;
    }

    public void setTrack(AlbumNameObject albumNameObject) {
        this.albumNameObject = albumNameObject;
    }

    public AlbumObject getAlbum() {
        return albumObject;
    }

    public void setAlbum(AlbumObject albumObject) {
        this.albumObject = albumObject;
    }

    public AlbumsListObject getTopalbums() {
        return topAlbums;
    }

    public void setTopalbums(AlbumsListObject topAlbums) {
        this.topAlbums = topAlbums;
    }

    public TrackmatchesObject getResults() {
        return results;
    }

    public void setResults(TrackmatchesObject results) {
        this.results = results;
    }

    /**
     * returns the album
     * @return album as string
     */
    public String getAlbumToString(){return albumObject.toString();}

    /**
     * returns album title
     * @return album title as string
     */
    public String getAlbumNameUsingTrackAndArtistToString(){
        if(albumNameObject == null || albumNameObject.toString() == null){
            return null;
        }
        return albumNameObject.toString();
    }

    /**
     * returns a list of album names
     * @return list of album names as string
     */
    public String getAlbumsNameUsingArtistToString(){return topAlbums.toString();}

    /**
     * returns a list of artists
     * @return list of artists as string
     */
    public ArrayList<String> getArtistUsingTrack(){
        return results.getArtistList();
    }

}
