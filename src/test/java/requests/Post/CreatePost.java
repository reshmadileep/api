package requests.Post;

import java.util.*;
import stepDefintions.World;
import requests.PostRequestBaseClass;
import businessObjects.Post;


public class CreatePost extends PostRequestBaseClass{
    private String uuid;

    public CreatePost(World world) {
        super(world);
        this.world = world;
        uuid = UUID.randomUUID().toString();
        this.world.getContext().put("uuid", uuid);
        this.baseUrl = ((HashMap<String, String>) this.world.getContext().get("config")).get("postApiUrl");
    }

    public CreatePost updateRequest(String condition){
        setBusinessObject(new Post(uuid));
        return this;
    }

}
