package com.heung.forme.custom.recommendation;

import java.util.HashMap;
import java.util.Map;

public class Login {
    static Map<String, String> usernameToID = new HashMap<String, String>(){
        {
            put("hpark0811", "4806f34e-93a6-4e2f-9e41-5ee9f0d24f14");
            put("yjeon27", "f2d298fd-8c7c-4c10-83a4-967328686cc0");
            put("jhwan7", "f2d298fd-8c7c-4c10-83a4-967328686cc0");
            put("songsong", "f2d298fd-8c7c-4c10-83a4-967328686cc0");
        }
    };

    static public boolean checkLogin(String userID, String password){
        return usernameToID.containsKey(userID);
    }

    static public String getUserID(String userID){
        return usernameToID.get(userID);
    }
}
