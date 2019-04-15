package requests.Post;

import requests.GetRequestBaseClass;
import stepDefintions.World;

import java.util.HashMap;

public class RetrievePost extends GetRequestBaseClass {
    private World world;

    public RetrievePost(World world) {
        super(world);
        this.world = world;
        this.baseUrl = ((HashMap<String, String>) this.world.context.get("config")).get("postApiUrl");
    }

    public RetrievePost updateRequest(String condition) {
        switch (condition) {
            case "valid details":
                baseUrl = baseUrl + "/1";
                break;
            case "invalid details":
                baseUrl = baseUrl + "/invalidId";
                break;
            case "verify post":
                baseUrl = baseUrl + "/" + world.context.get("createdId").toString();
                break;
        }
        return this;
    }
}
