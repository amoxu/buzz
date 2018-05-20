package com.amoxu.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class FeatherThreadPool {
    @Autowired
    TaskExecutor featherExecutor;

    public boolean addUserFeather() {
        return false;
    }


}
