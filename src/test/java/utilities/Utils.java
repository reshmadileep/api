package utilities;

import com.google.gson.Gson;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.util.HashMap;
import stepDefintions.World;

import static org.junit.Assert.assertTrue;

public class Utils {

    @SuppressWarnings("unchecked")
	public static Object createBusinessObjectFromClassName(Object object, Class className) {
        Gson gson = new Gson();
        return gson.fromJson(gson.toJson(object), className);
    }

    public static void assertThatObjectsAreEqual(Object expected, Object actual) {
        Gson gson = new Gson();
        assertTrue("The objects are not equal. \nExpected : " + gson.toJson(expected) + " \nActual : " + gson.toJson(actual), EqualsBuilder.reflectionEquals(expected, actual));
    }

}
