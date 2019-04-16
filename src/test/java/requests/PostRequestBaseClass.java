package requests;

import businessObjects.BaseBusinessObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefintions.World;
import utilities.Utils;

import static org.hamcrest.Matchers.*;

public abstract class PostRequestBaseClass {
	protected World world;
//    protected JSONObject requestBody = new JSONObject();
    private BaseBusinessObject businessObject = null;
    private ContentType contentType = ContentType.JSON;
    private ContentType accept = ContentType.JSON;
	private String url = "";
	protected String baseUrl = "";
	private RequestSpecification request;
	private Response response;

    public PostRequestBaseClass(World world) {
        this.world = world;
    }

    public BaseBusinessObject getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(BaseBusinessObject businessObject) {
        this.businessObject = businessObject;
    }

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
        this.world.getContext().put("response", response);
		return this;
	}
	
	public PostRequestBaseClass validateErrorResponse( String condition){
		response.then().assertThat().statusCode(equalTo(400));
		return this;
	}

	public PostRequestBaseClass validateSuccessResponse(String condition, Class className) {
		response.then().assertThat().statusCode(equalTo(201));
        BaseBusinessObject responseObject = response.body().as(businessObject.getClass(), ObjectMapperType.GSON);
        businessObject.setId(responseObject.getId());
        Utils.assertThatObjectsAreEqual(businessObject,responseObject);
		return this;
	}
}