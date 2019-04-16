package com.restApp.rest;

import com.restApp.PostTask;
import com.restApp.PostTasks;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class RestPostTaks implements PostTasks {


    @Inject
    RestConfig restConfig;


    @Override
    public PostTask post() {
        return new RestPostTask(restConfig);
    }
}
