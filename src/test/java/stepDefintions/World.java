package stepDefintions;

import java.util.HashMap;
import java.util.Map;

public class World {
    private Map<String,Object> context;

    public World() {
        context = new HashMap<>();
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

    public void add2Context(String key, Object value){
        this.context.put(key,value);
    }
}
