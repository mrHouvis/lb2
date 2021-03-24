package shelekhovdenis.models.track;

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

    @Override
    public String toString() {
        return artist;
    }
}
