package com.reqres.test.automation.scenario;

import com.restApp.PostTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;


@Service
public class ScenarioGenerator {

    @Inject
    private PostTasks postTasks;

    private static final Logger LOGGER = LoggerFactory.getLogger(ScenarioGenerator.class);
    public PostTasks postTasksBuilder() {
        return postTasks;
    }


    private ExecutionType executionType;

    public ScenarioGenerator(ExecutionType executionType) {
        this.executionType = executionType;
        LOGGER.info("Constructed ScenarioGenerator for {} execution", executionType.name());
    }

    public ExecutionType getExecutionType() {
        return executionType;
    }


    public enum ExecutionType {
        REST,
        IN_MEMORY
    }




}
