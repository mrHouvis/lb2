package shelekhovdenis.models.artists;

/**
 * contains the title of the album
 */
public class AlbumForArtistObject {

    String name;

    public AlbumForArtistObject(){}

    public AlbumForArtistObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the name of the album
     * @return name of the album as string
     */
    @Override
    public String toString() {
        return name;
    }
}
