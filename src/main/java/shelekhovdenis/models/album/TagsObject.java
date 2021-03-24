package shelekhovdenis.models.album;

import java.util.ArrayList;

public class TagsObject {

    ArrayList<Object> tag = new ArrayList<>();
    private ArrayList<String> name = new ArrayList<>();

    @Override
    public String toString() {

        for(Object temp : tag){
            String[] str = temp.toString().split(",");
            name.add(str[0].replace("{name=", ""));
        }
        StringBuilder str = new StringBuilder("tags{");
        for(int i = 0; i < name.size(); i++){
            if(i != 0){
                str.append(", ");
            }
            str.append(name.get(i));
        }
        str.append("}");
        return str.toString();

    }

}
