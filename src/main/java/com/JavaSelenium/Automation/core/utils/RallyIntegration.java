package com.JavaSelenium.Automation.core.utils;
import com.JavaSelenium.Automation.core.logger.Logs;
import com.google.gson.JsonObject;
import com.rallydev.rest.RallyRestApi;
import com.rallydev.rest.request.CreateRequest;
import com.rallydev.rest.request.QueryRequest;
import com.rallydev.rest.response.CreateResponse;
import com.rallydev.rest.response.QueryResponse;
import com.rallydev.rest.util.Fetch;
import com.rallydev.rest.util.QueryFilter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
public class RallyIntegration {

    public static void updatedTestCaseStatusInRally(String tcID,int resultStatus,String buildNumber, String notes) throws Exception{
        try{
            String testCaseStatus = "";
            RallyRestApi restApi = new RallyRestApi(new URI("https://rally1.rallydev.com"), "TestUserName","TestPassword");
            restApi.setProxy(new URI("http://TestProxy"), "TestUserName", "TestPassword");
            QueryRequest testCaseRequest = new QueryRequest("TestCase");
            testCaseRequest.setFetch(new Fetch("FormattedID","Name","Workspace"));
            testCaseRequest.setQueryFilter(new QueryFilter("FormattedID", "=", tcID));
            QueryResponse testCaseQueryResponse = restApi.query(testCaseRequest);
            JsonObject testCaseJsonObject = testCaseQueryResponse.getResults().get(0).getAsJsonObject();
            JsonObject newTestCaseResult = new JsonObject();
            String testCaseRef = testCaseJsonObject.get("_ref").getAsString();
            if(resultStatus==1){ testCaseStatus = "Pass"; }
            else if(resultStatus==2){ testCaseStatus = "Fail"; }
            newTestCaseResult.addProperty("Build", buildNumber);
            newTestCaseResult.addProperty("Verdict", testCaseStatus);
            java.util.Date date= new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            String timestamp = sdf.format(date);
            newTestCaseResult.addProperty("Date", timestamp);
            newTestCaseResult.addProperty("Notes", notes);
            newTestCaseResult.addProperty("TestCase", testCaseRef);
            CreateRequest createRequest = new CreateRequest("testcaseresult", newTestCaseResult);
            CreateResponse createResponse = restApi.create(createRequest);
            if(createResponse.wasSuccessful()){ Logs.info("TestCase with Id:"+tcID+" is updated successfully in Rally"); }
            else { Logs.error("Failed to update the TestCase status with Id:"+tcID+" in Rally"); }
            restApi.close();
        }
        catch (IOException e){ Logs.error("Authentication error --"+e); }
        catch (URISyntaxException e) { Logs.error("Exception occurred in method updatedTestCaseStatusInRally "+e); }
        catch (Exception e) { Logs.error("Rally Updates issue --" + e); }
    }
}

