package businessObjects;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Post extends BaseBusinessObject{
    public String title,body,userID;

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
