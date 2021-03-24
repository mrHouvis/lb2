package shelekhovdenis.models.track;

import java.util.ArrayList;

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

    public ArrayList<String> getArtistList(){
        ArrayList<String> artistList = new ArrayList<>();
        for(TrackObject track : trackList){
            artistList.add(track.toString());
        }
        return artistList;
    }

}
