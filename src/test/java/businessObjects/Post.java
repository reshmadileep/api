package businessObjects;

public class Post extends BaseBusinessObject{
    public String title,body,userID;

    public Post(String uuid){
        title = "New Post - "+ uuid;
        body = "This is the body of this wonderful post - " + uuid;
        userID = uuid;
    }
}
