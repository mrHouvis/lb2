package shelekhovdenis.models.artists;

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

    @Override
    public String toString() {
        return name;
    }
}
