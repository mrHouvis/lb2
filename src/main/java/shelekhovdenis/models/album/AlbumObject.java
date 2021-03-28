package shelekhovdenis.models.album;

import java.util.ArrayList;

public class AlbumObject {

    private String name;
    private String artist;
    private String title;
    ArrayList<Object> image = new ArrayList <> ();
    TracksSimpleObject tracksSimpleObject;
    TagsObject tagsObject;

    public AlbumObject() {
    }

    public AlbumObject(String name, String artist, String title, ArrayList<Object> image, TracksSimpleObject tracksSimpleObject, TagsObject tagsObject) {
        this.name = name;
        this.artist = artist;
        this.title = title;
        this.image = image;
        this.tracksSimpleObject = tracksSimpleObject;
        this.tagsObject = tagsObject;
    }

    public String getName() {
        return name;
    }

    public TracksSimpleObject getTracks() {
        return tracksSimpleObject;
    }

    public TagsObject getTags() {
        return tagsObject;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public void setTracks(TracksSimpleObject tracksSimpleObject) {
        this.tracksSimpleObject = tracksSimpleObject;
    }

    public void setTags(TagsObject tagsObject) {
        this.tagsObject = tagsObject;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Album{" + "\n\tname=\"" + name + "\"" + ";\n\tartist=\"" + artist + "\";\n\t");
        str.append("images{").append(imageToString()).append("\n\t};\n\t").append("tags{").append(tagsObject).append("};\n\t");
        if(tracksSimpleObject == null){
            return str.toString();
        }
        str.append("tracks{").append(tracksSimpleObject).append("\n\t}").append("\n}");
        return str.toString();
    }

    public String imageToString(){

        ArrayList<String> link = new ArrayList<>();
        ArrayList<String> size = new ArrayList<>();

        for(Object temp : image){
            String[] str = temp.toString().split(",");
            link.add(str[0].replace("{#text=", ""));
            String img = str[1].replace(" size=", "");
            size.add(img.replace("}", ""));
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < link.size(); i++){
            str.append("\n\t\tlink=\"").append(link.get(i)).append("\", size=").append(size.get(i)).append(";");
        }
        return str.toString();

    }

}
