package com.javatesting.messenger.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/javatesting/messenger/features",
        monochrome = true,
        plugin = {"json:target/cucumber-report/report.json","pretty"},
        glue = {"com.javatesting.messenger.cucumberstepdefinitions"}
)

public class TestRunner {

}
