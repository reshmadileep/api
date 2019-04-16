package requests;

import static org.hamcrest.Matchers.equalTo;
import businessObjects.BaseBusinessObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefintions.World;
import utilities.Utils;

public abstract class GetRequestBaseClass {
    protected World world;
    protected BaseBusinessObject businessObject = null;
    protected ContentType accept = ContentType.JSON;
    protected String url = "";
    protected String baseUrl = "";
    protected RequestSpecification request;
    protected Response response;
    protected boolean isSingleItemReturned = false;

    public GetRequestBaseClass(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public BaseBusinessObject getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(BaseBusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    public ContentType getAccept() {
        return accept;
    }

    public void setAccept(ContentType accept) {
        this.accept = accept;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isSingleItemReturned() {
        return isSingleItemReturned;
    }

    public void setSingleItemReturned(boolean singleItemReturned) {
        isSingleItemReturned = singleItemReturned;
    }

    public GetRequestBaseClass createRequest() {
        request = RestAssured.given()
        		.baseUri(baseUrl)
        		.accept(accept);
        return this;
    }

    public GetRequestBaseClass get() {
//        RestAssuredConfig config = RestAssured.config().sslConfig(new SSLConfig()
//             .relaxedHTTPSValidation().allowAllHostnames());
//        request = request.config(config);
        response = request.log().all(true).get(url);
        response.then().log().all(true);
        world.add2Context("apiResponse", response);
        return this;
}


	public GetRequestBaseClass validateSuccessResponse(String condition, Class className) {
    	response.then().assertThat().statusCode(equalTo(200));
//        Object expectedObject;
//        Object actualObject;
        int listSize = 0;
        try {
            listSize = response.body().jsonPath().getList("").size();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(isSingleItemReturned)
        {
            //expectedObject = Utils.createBusinessObjectFromClassName(db.response.body().jsonPath().getList("_embedded").get(0),className);
            //actualObject = response.body().as(className, ObjectMapperType.GSON);
            //Utils.assertThatObjectsAreEqual(expectedObject, actualObject);
            response.body().as(className, ObjectMapperType.GSON);
        }
        else {
            for(int i =0; i<listSize; i++) {
                //expectedObject = Utils.createBusinessObjectFromClassName(db.response.body().jsonPath().getList("_embedded").get(i),className);
                //actualObject =  Utils.createBusinessObjectFromClassName(response.body().jsonPath().getList("").get(i), className);
                //Utils.assertThatObjectsAreEqual(expectedObject, actualObject);
                Utils.createBusinessObjectFromClassName(response.body().jsonPath().getList("").get(i), className);
            }
        }
    	return this;
    }
    
    public GetRequestBaseClass validateErrorResponse(String condition) {
        if (condition.equals("invalid details")) {
            response.then().assertThat().statusCode(equalTo(404));
            response.then().assertThat().statusLine(equalTo("HTTP/1.1 404 Not Found"));
        }
    	return this;
    }
}

