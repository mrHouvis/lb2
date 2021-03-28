package shelekhovdenis.models.track;

import java.util.ArrayList;

/**
 * contains a class TrackListObject object for parse
 */
public class TrackmatchesObject {

    private TrackListObject trackmatches;

    public TrackmatchesObject(){}

    public TrackmatchesObject(TrackListObject trackmatches) {
        this.trackmatches = trackmatches;
    }

    public TrackListObject getTrackmatches() {
        return trackmatches;
    }

    public void setTrackmatches(TrackListObject trackmatches) {
        this.trackmatches = trackmatches;
    }

    /**
     * returns a list of artists
     * @return list of artists as string
     */
    public ArrayList<String> getArtistList(){
        return trackmatches.getArtistList();
    }
}
