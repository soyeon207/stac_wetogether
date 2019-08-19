package com.example.we_together;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebaseJoin {

    public String id; //아이디
    public String pwd; //비밀번호
    public String name; //이름

    public FirebaseJoin() {}

    public FirebaseJoin(String pwd,String name, String id){
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }

    @Exclude
    public Map<String,Object> toMap() {
        HashMap<String,Object> result = new HashMap<>();
        result.put("pwd",pwd);
        result.put("name",name);
        result.put("id",id);
        return result;
    }
}
