package businessObjects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Post extends BaseBusinessObject{
    private String title;
    private String body;
    private String userID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Post(String uuid){
        title = "New Post - "+ uuid;
        body = "This is the body of this wonderful post - " + uuid;
        userID = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Post)) return false;

        Post post = (Post) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(title, post.title)
                .append(body, post.body)
                .append(userID, post.userID)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(title)
                .append(body)
                .append(userID)
                .toHashCode();
    }
}
