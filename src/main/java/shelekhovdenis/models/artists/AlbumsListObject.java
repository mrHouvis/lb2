package shelekhovdenis.models.artists;

import java.util.ArrayList;

/**
 * contains a list of album titles
 */
public class AlbumsListObject {

    ArrayList<AlbumForArtistObject> album;

    public AlbumsListObject(){
    }

    public AlbumsListObject(ArrayList<AlbumForArtistObject> album) {
        this.album = album;
    }

    public ArrayList<AlbumForArtistObject> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<AlbumForArtistObject> album) {
        this.album = album;
    }

    /**
     * lists album names
     * @return lists album names as string
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < album.size(); i++){
            if(i != 0){
                str.append(", ");
            }
            str.append(album.get(i).toString());
        }
        return str.toString();
    }
}
