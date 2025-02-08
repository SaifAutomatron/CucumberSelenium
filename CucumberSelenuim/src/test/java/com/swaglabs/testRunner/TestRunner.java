package com.swaglabs.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"com.swaglabs.steps"},
		plugin = {"pretty", "json:target/json-report/cucumber.json"},
		dryRun = false,
		monochrome = true,
		tags = "@sc2 or @sc3"
		//name = {"item"}
		)
public class TestRunner {
	

}
