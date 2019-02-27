package stepDefintions.hooks;

import java.util.*;
import stepDefintions.World;

public class ScenarioHooks {
	    private World world;
	    HashMap<String, String> config;
	    HashMap<String, Object> context;
	    String nullStr = null;


	    @SuppressWarnings("unchecked")
	    public ScenarioHooks(World world) {
	        this.world = world;
	        config = (HashMap<String, String>)this.world.context.get("config");
	        context = this.world.context;
	    }

}
