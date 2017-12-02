package helper;

import java.util.ArrayList;
import java.util.List;

public class StringHelper {

    public String cutCommand(String str){
        int position = str.indexOf(' ');

        if (position != -1) return str.substring(0, position);
        else return str;
    }

    public List<String> cutAdditionalParametrs(String str){
        List<String> parametrs = new ArrayList<String>();

        int position = str.indexOf(' ');

        if(position != -1){
            str = str.substring(position+1);

            while (str.indexOf(' ') != -1){
                parametrs.add(str.substring(0,str.indexOf(' ')));
                str = str.substring(str.indexOf(' ')+1);
            }

            parametrs.add(str);
        }

        return parametrs;
    }

}
