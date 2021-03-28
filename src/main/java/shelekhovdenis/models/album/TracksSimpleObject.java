package shelekhovdenis.models.album;

import java.util.ArrayList;

/**
 * contains a list of tracks (title, duration)
 */
public class TracksSimpleObject {

    ArrayList<Object> track = new ArrayList <> ();
    private ArrayList<String> name;
    private ArrayList<String> duration;

    /**
     * returns a list of tracks
     * @return list of tracks as string
     */
    @Override
    public String toString() {

        name = new ArrayList<>();
        duration = new ArrayList<>();

        if(track == null){
            return null;
        }

        for(Object temp : track){
            String[] str = temp.toString().split(",");
            name.add(str[7].replace(" name=", ""));
            duration.add(str[0].replace("{duration=", ""));
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < name.size(); i++){
            str.append("\n\t\tname=\"").append(name.get(i)).append("\", duration=\"").append(duration.get(i)).append("\";");
        }
        return str.toString();
    }

    /**
     * returns a list of tracks
     * @return list of tracks as ArrayList
     */
    public ArrayList<String> getTracksToFile(){
        ArrayList<String> tracks = new ArrayList<>();
        for (int i = 0; i < name.size(); i++){
            tracks.add("name: \"" + name.get(i) + "\", duration=" + duration.get(i) + ";");
        }
        return tracks;
    }

}
