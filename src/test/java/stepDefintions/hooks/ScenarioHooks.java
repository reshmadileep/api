package stepDefintions.hooks;

import java.util.*;
import stepDefintions.World;

public class ScenarioHooks {
	    private World world;
	    Map<String, String> config;
	    Map<String, Object> context;
	    String nullStr = null;


	    @SuppressWarnings("unchecked")
	    public ScenarioHooks(World world) {
	        this.world = world;
	        config = (Map<String, String>)this.world.getContext().get("config");
	        context = this.world.getContext();
	    }

}
