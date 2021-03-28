package shelekhovdenis.models.track;

/**
 * contains the artist's name
 */
public class TrackObject {

    private String artist;

    public TrackObject(){}

    public TrackObject(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * returns the artist name
     * @return artist name as string
     */
    @Override
    public String toString() {
        return artist;
    }
}
