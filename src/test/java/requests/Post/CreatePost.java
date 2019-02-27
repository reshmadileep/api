package requests.Post;

import java.util.*;
import stepDefintions.World;
import requests.PostRequestBaseClass;
import businessObjects.Post;


public class CreatePost extends PostRequestBaseClass{
    private World world;
    String uuid;

    public CreatePost(World world) {
        super(world);
        this.world = world;
        uuid = UUID.randomUUID().toString();
        this.world.context.put("uuid", uuid);
        this.baseUrl = ((HashMap<String, String>) this.world.context.get("config")).get("postApiUrl");
    }

    public CreatePost UpdateRequest(String condition){
        Post post = new Post(uuid);
        switch (condition) {
            case "mandatory information":
                //do nothing as mandatory info is added by default
                break;
        }

        businessObject = post;
        return this;
    }

}
