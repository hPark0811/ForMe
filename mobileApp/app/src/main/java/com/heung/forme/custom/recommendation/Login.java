package com.heung.forme.custom.recommendation;

import java.util.HashMap;
import java.util.Map;

public class Login {
    static Map<String, String> usernameToID = new HashMap<String, String>(){
        {
            put("aa", "24a794a3-ea5b-4245-bd7a-eb8466f4eb7d");
            put("bb", "f2d298fd-8c7c-4c10-83a4-967328686cc0");
            put("cc", "0197dacf-35ce-4060-9be5-3d030b1eeb8d");
            put("dd", "8f5b8262-ff6c-47a3-9b9e-387c829e3403");
        }
    };

    static public boolean checkLogin(String userID, String password){
        return usernameToID.containsKey(userID);
    }

    static public String getUserID(String userID){
        return usernameToID.get(userID);
    }
}
