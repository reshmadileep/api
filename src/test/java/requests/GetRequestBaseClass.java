package requests;

import static org.hamcrest.Matchers.equalTo;
import businessObjects.BaseBusinessObject;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefintions.World;
import utilities.Utils;

public abstract class GetRequestBaseClass {
	private World world;
	public BaseBusinessObject businessObject = null;
    public ContentType accept = ContentType.JSON;
    public String url = "";
    public String baseUrl = "";
    public RequestSpecification request;
    public Response response;
    public boolean isSingleItemReturned = false;

    public GetRequestBaseClass(World world) {
        this.world = world;
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
        world.context.put("apiResponse", response);
        return this;
}


	public GetRequestBaseClass validateSuccessResponse(String condition, Class className) {
    	response.then().assertThat().statusCode(equalTo(200));
        Object expectedObject, actualObject;
        int listSize = 0;
        try {
            listSize = response.body().jsonPath().getList("").size();
        }catch (Exception e){}
        if(isSingleItemReturned)
        {
            //expectedObject = Utils.createBusinessObjectFromClassName(db.response.body().jsonPath().getList("_embedded").get(0),className);
            actualObject = response.body().as(className, ObjectMapperType.GSON);
            //Utils.assertThatObjectsAreEqual(expectedObject, actualObject);
        }
        else {
            for(int i =0; i<listSize; i++) {
                //expectedObject = Utils.createBusinessObjectFromClassName(db.response.body().jsonPath().getList("_embedded").get(i),className);
                actualObject =  Utils.createBusinessObjectFromClassName(response.body().jsonPath().getList("").get(i), className);
                //Utils.assertThatObjectsAreEqual(expectedObject, actualObject);
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

