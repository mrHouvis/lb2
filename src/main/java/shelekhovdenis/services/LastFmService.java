package shelekhovdenis.services;

import shelekhovdenis.models.album.AlbumObject;
import shelekhovdenis.models.artists.AlbumsListObject;
import shelekhovdenis.models.track.TrackmatchesObject;
import shelekhovdenis.models.trackandartist.AlbumNameObject;

import java.util.ArrayList;

public class LastFmService {

    AlbumNameObject albumNameObject;
    AlbumObject albumObject;
    AlbumsListObject topAlbums;
    TrackmatchesObject results;

    public LastFmService() {
    }

    public LastFmService(AlbumNameObject albumNameObject) {
        this.albumNameObject = albumNameObject;
    }

    public LastFmService(AlbumObject albumObject) {
        this.albumObject = albumObject;
    }

    public LastFmService(AlbumsListObject topAlbums) {
        this.topAlbums = topAlbums;
    }

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

    public String getAlbumToString(){return albumObject.toString();}

    public String getAlbumNameUsingTrackAndArtistToString(){
        if(albumNameObject == null || albumNameObject.toString() == null){
            return null;
        }
        return albumNameObject.toString();
    }

    public String getAlbumsNameUsingArtistToString(){return topAlbums.toString();}

    public ArrayList<String> getArtistUsingTrack(){
        return results.getArtistList();
    }

}
