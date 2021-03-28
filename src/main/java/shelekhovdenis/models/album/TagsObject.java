package shelekhovdenis.models.album;

import java.util.ArrayList;

/**
 * a class that contains a list of tags (genres) of the album
 */
public class TagsObject {

    ArrayList<Object> tag;
    private ArrayList<String> name;

    /**
     * returns a list of tags
     * @return list of tags as string
     */
    @Override
    public String toString() {

        name = new ArrayList<>();

        for(Object temp : tag){
            String[] str = temp.toString().split(",");
            name.add(str[0].replace("{name=", ""));
        }
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < name.size(); i++){
            if(i != 0){
                str.append(", ");
            }
            str.append(name.get(i));
        }
        return str.toString();

    }

}
