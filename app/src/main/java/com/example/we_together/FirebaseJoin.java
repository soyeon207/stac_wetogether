package com.example.we_together;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebaseJoin {

    public String code; //아이디
    public String name; //비밀번호

    public FirebaseJoin() {}

    public FirebaseJoin(String code,String name){
        this.code = code;
        this.name = name;
    }

    @Exclude
    public Map<String,Object> toMap() {
        HashMap<String,Object> result = new HashMap<>();
        result.put("code",code); //code : code
        result.put("name",name); //name : name
        return result;
    }
}
