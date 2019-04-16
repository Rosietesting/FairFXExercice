package com.restApp;

import com.restApp.rest.Post;

public interface PostTask extends Execute<PostTask>  {

    PostTask withUserId(Integer userId);
    PostTask withId(Integer userId);
    PostTask withTitle(String title);
    PostTask withBody(String body);

}
