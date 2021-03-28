package shelekhovdenis.models.track;

import java.util.ArrayList;

/**
 * contains a list of artists
 */
public class TrackListObject {

    private ArrayList<TrackObject> trackList;

    public TrackListObject(){}

    public TrackListObject(ArrayList<TrackObject> trackList) {
        this.trackList = trackList;
    }

    public ArrayList<TrackObject> getTrack() {
        return trackList;
    }

    public void setTrack(ArrayList<TrackObject> trackmatches) {
        this.trackList = trackmatches;
    }

    /**
     * composes and returns a list of artists
     * @return list of artists as string
     */
    public ArrayList<String> getArtistList(){
        ArrayList<String> artistList = new ArrayList<>();
        for(TrackObject track : trackList){
            artistList.add(track.toString());
        }
        return artistList;
    }

}
