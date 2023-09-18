package Helpers;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Dictionary;
import java.util.Enumeration;

import static io.restassured.http.Method.*;

public class ApiAccessHelper {

    private Response response;
    private String accessToken;
    public static Response lastResponse;
    public Response sendRequest(String endpoint, Method reqMethod, Dictionary<String, String> paramList, Dictionary<String, String> additionalHeaderList, String reqBody) {
        RequestSpecification request = RestAssured.given();
        accessToken = "";
        if (additionalHeaderList != null)
        {
            Enumeration<String> additionalHeaders = additionalHeaderList.keys();
            while(additionalHeaders.hasMoreElements())
            {
                String headerKey = additionalHeaders.nextElement();
                request.header(headerKey, additionalHeaderList.get(headerKey));
            }
        }
        if (paramList != null)
        {
            Enumeration<String> params = paramList.keys();
            while(params.hasMoreElements())
            {
                String paramKey = params.nextElement();
                request.header(paramKey, paramList.get(paramKey));
            }
        }
        if (reqBody != null)
        {
            request.body(reqBody.toString());
            request.contentType("text/json");
        }
        request.header("Authorization", "Bearer " + accessToken);
        if (reqMethod.equals(GET)) {
            response = request.get(endpoint);
            System.out.println(response);
        } else if(reqMethod.equals(POST)) {
            response = request.post(endpoint);
        }else if(reqMethod.equals(PUT)) {
            response = request.put(endpoint);
        }else if(reqMethod.equals(DELETE)) {
            response = request.delete(endpoint);
        }
        lastResponse = response;
        return response;
    }
}
