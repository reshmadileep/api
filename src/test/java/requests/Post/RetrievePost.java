package requests.Post;

import requests.GetRequestBaseClass;
import stepDefintions.World;

import java.util.HashMap;

public class RetrievePost extends GetRequestBaseClass {

    public RetrievePost(World world) {
        super(world);
        this.world = world;
        this.baseUrl = ((HashMap<String, String>) this.world.getContext().get("config")).get("postApiUrl");
    }

    public RetrievePost updateRequest(String condition) {
        switch (condition) {
            case "invalid details":
                baseUrl = baseUrl + "/invalidId";
                break;
            case "verify post":
                baseUrl = baseUrl + "/" + world.getContext().get("createdId").toString();
                break;
            default:
                baseUrl = baseUrl + "/1";
                break;
        }
        return this;
    }
}
