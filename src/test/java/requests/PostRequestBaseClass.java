package requests;

import java.util.*;
import org.json.simple.JSONObject;

import businessObjects.BaseBusinessObject;
import io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefintions.World;
import utilities.Utils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import static org.hamcrest.Matchers.*;

public abstract class PostRequestBaseClass {
	private World world;
	public JSONObject requestBody = new JSONObject();
	public BaseBusinessObject businessObject = null;
	public ContentType contentType = ContentType.JSON;
	public ContentType accept = ContentType.JSON;
	public String url = "";
	public String baseUrl = "";
	public RequestSpecification request;
	public Response response;

    public PostRequestBaseClass(World world) {
        this.world = world;
    }

	@SuppressWarnings("unchecked")
	public PostRequestBaseClass createRequest() {
		request = RestAssured.given();
        request.baseUri(baseUrl);
        request.accept(accept);
        request.contentType(contentType);
        request.body(businessObject, ObjectMapperType.GSON);
		return this;
	}
	
	public PostRequestBaseClass post() {
		response = request.log().all(true).post(url);
        response.then().log().all(true);
        this.world.context.put("response", response);
		return this;
	}
	
	public PostRequestBaseClass validateErrorResponse( String condition, Class className){
		response.then().assertThat().statusCode(equalTo(400));
		return this;
	}

	public PostRequestBaseClass validateSuccessResponse(String condition, Class className) {
		response.then().assertThat().statusCode(equalTo(201));
        BaseBusinessObject responseObject = response.body().as(businessObject.getClass(), ObjectMapperType.GSON);
        businessObject.id = responseObject.id;
        Utils.assertThatObjectsAreEqual(businessObject,responseObject);
		return this;
	}
}