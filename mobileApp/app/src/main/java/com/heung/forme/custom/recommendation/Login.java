package com.heung.forme.custom.recommendation;

import java.util.HashMap;
import java.util.Map;

public class Login {
    static Map<String, String> usernameToID = new HashMap<String, String>(){
        {
            put("hpark0811", "asdfasdfasdfasdf");
            put("yjeon27", "fdsafdsafdsafdsa");
            put("jhwan7", "fdsaasdffdsaasdf");
            put("songsong", "sdfdsfsfdsdfs");
        }
    };

    static public boolean checkLogin(String userID, String password){
        return usernameToID.containsKey(userID);
    }

    static public String getUserID(String userID){
        return usernameToID.get(userID);
    }
}
