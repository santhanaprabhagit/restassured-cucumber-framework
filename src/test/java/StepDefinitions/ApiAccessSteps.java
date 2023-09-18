package StepDefinitions;

import Helpers.ApiAccessHelper;
import io.cucumber.java.en.Then;
import org.apache.http.HttpStatus;
import org.testng.Assert;

public class ApiAccessSteps {
    @Then("^The response code receives is (.*?)$")
    public void theResponseCodeReceives(String expectedResponseCode) {
        int expRespCode;
        switch (expectedResponseCode) {
            case "OK":
                expRespCode = HttpStatus.SC_OK;
                break;
            case "Created":
                expRespCode = HttpStatus.SC_CREATED;
                Assert.assertEquals(expRespCode, ApiAccessHelper.lastResponse.statusCode(), "HTTP response code is not as expected");
                break;
            case "NoContent":
                expRespCode = HttpStatus.SC_NO_CONTENT;
                break;
            case "NotFound":
                expRespCode = HttpStatus.SC_NOT_FOUND;;
                break;
            case "InternalServerErr":
                expRespCode = HttpStatus.SC_INTERNAL_SERVER_ERROR;;
                break;
            case "Unauthorized":
                expRespCode = HttpStatus.SC_UNAUTHORIZED;
                break;
            case "Forbidden":
                expRespCode = HttpStatus.SC_FORBIDDEN;
                break;
            case "BadRequest":
                expRespCode = HttpStatus.SC_BAD_REQUEST;
                break;
            case "Conflict":
                expRespCode = HttpStatus.SC_CONFLICT;
                break;
            default:
                Assert.fail("Expected response " + expectedResponseCode + " is not known");
                expRespCode = HttpStatus.SC_METHOD_NOT_ALLOWED;       // Any abitrary error code to assign the var. with value
                break;
        }
        Assert.assertEquals(expRespCode, ApiAccessHelper.lastResponse.statusCode(), "HTTP response code is not as expected");
    }
}
