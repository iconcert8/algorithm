import java.util.*;
class Solution {
    public String solution(String new_id) {
        //1
        new_id = new_id.toLowerCase(Locale.ROOT);

        final String regex = "(?![a-zA-Z0-9\\-\\_\\.]).";
        //2
        new_id = new_id.replaceAll(regex, "");

        //3
        new_id = new_id.replaceAll("[.]{2,}+", ".");

        //4
        if (new_id.charAt(0) == '.')
            new_id = new_id.substring(1);
        if (!new_id.isEmpty()) {
            if (new_id.charAt(new_id.length() - 1) == '.')
                new_id = new_id.substring(0, new_id.length() - 1);
        }

        //5
        if (new_id.isEmpty())
            new_id = "a";

        //6
        if (new_id.length() >= 15)
            new_id = new_id.substring(0, 15);
        if (new_id.charAt(new_id.length() - 1) == '.')
            new_id = new_id.substring(0, new_id.length() - 1);

        //7
        if (new_id.length() <= 2) {
            char lastChar = new_id.charAt(new_id.length() - 1);
            while (new_id.length() < 3) {
                new_id += lastChar;
            }
        }
        
        

        return new_id;
    }
}