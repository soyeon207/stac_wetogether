package com.example.we_together;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FirebaseName {

    public String name; //아이디
    public String check;

    public FirebaseName() {}

    public FirebaseName(String name,String chk){
        if(chk.equals(" "))
            check = "name";
        else
            check=chk;
        this.name = name;
    }

    @Exclude
    public Map<String,Object> toMap() {
        HashMap<String,Object> result = new HashMap<>();
        result.put(check,name); //name : name
        return result;
    }



}
