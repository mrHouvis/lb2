package shelekhovdenis.models.album;

import java.util.ArrayList;

public class TagsObject {

    ArrayList<Object> tag;
    private ArrayList<String> name;

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
