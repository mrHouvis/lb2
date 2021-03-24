package shelekhovdenis.models.trackandartist;


import shelekhovdenis.models.album.AlbumObject;

public class AlbumNameObject {

    private AlbumObject album;

    public AlbumNameObject(){
    }

    public AlbumNameObject(AlbumObject album) {
        this.album = album;
    }

    public AlbumObject getAlbum() {
        return album;
    }

    public void setAlbum(AlbumObject album) {
        this.album = album;
    }

    @Override
    public String toString() {
        if(album == null){
            return null;
        }
        return album.getTitle();
    }
}
