package StepDefinitions;

import Managers.FileReaderManager;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {


    @Before
    public void setUp() {
        RestAssured.baseURI = FileReaderManager.getInstance().getConfigFileReader().getUrl();
    }
}
