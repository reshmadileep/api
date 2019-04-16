package runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

@RunWith(Cucumber.class)

@CucumberOptions(features={"src/test/resources/features/"},
        glue = { "stepDefintions" },
        //tags= {"@retri"},
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                "html:target/cucumber-reports"},
        monochrome = true,
        dryRun = false,
        strict = false
)

public class RunnerTest {

    private RunnerTest(){}

    @AfterClass
    public static void writeCucumberHtmlReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("./target/cucumber-reports/Cucumber.json");


        String buildNumber = "1";
        String projectName = "TMF API E2E Automation";
        boolean runWithJenkins = false;
//    	boolean parallelTesting = false;
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc
        configuration.setRunWithJenkins(runWithJenkins);
        configuration.setBuildNumber(buildNumber);
        // Additional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
//    	configuration.addClassifications("Browser", "Firefox");
//    	configuration.addClassifications("Branch", "release/1.0");

        // optionally add metadata presented on main page via properties file
//    	List<String> classificationFiles = new ArrayList<>();
//    	classificationFiles.add("properties-1.properties");
//    	classificationFiles.add("properties-2.properties");
//    	configuration.addClassificationFiles(classificationFiles);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
//        Reportable result = reportBuilder.generateReports();
        reportBuilder.generateReports();
        // and here validate 'result' to decide what to do
        // if report has failed features, undefined steps etc
    }
}



