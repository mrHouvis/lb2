package shelekhovdenis.models.album;

import java.util.ArrayList;

public class TracksSimpleObject {

    ArrayList<Object> track = new ArrayList <> ();
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> duration = new ArrayList<>();

    @Override
    public String toString() {

        if(track == null){
            return null;
        }

        for(Object temp : track){
            String[] str = temp.toString().split(",");
            name.add(str[7].replace(" name=", ""));
            duration.add(str[0].replace("{duration=", ""));
        }
        StringBuilder str = new StringBuilder("tracks{");
        for(int i = 0; i < name.size(); i++){
            str.append("\n\t\tname=\"").append(name.get(i)).append("\", duration=\"").append(duration.get(i)).append("\";");
        }
        str.append("\n\t}");
        return str.toString();
    }

}
