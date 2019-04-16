package com.reqres.test.automation.scenario;


import com.reqres.test.config.RestItConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (classes = {RestItConfig.class})
public abstract class BaseScenarios {


}
