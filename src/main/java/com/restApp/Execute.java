package com.restApp;

import com.restApp.rest.Post;

import java.util.ArrayList;
import java.util.HashMap;

public interface Execute <T extends  Execute>{

    Post executePostPost();
    Post executeRetrievePost();
    Post executeUpdatePost();
    ArrayList<HashMap<String, String>> executeRemovePost();


}
