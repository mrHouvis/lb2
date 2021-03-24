package shelekhovdenis.models.track;

import java.util.ArrayList;

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

    public ArrayList<String> getArtistList(){
        return trackmatches.getArtistList();
    }
}
