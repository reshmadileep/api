package stepDefintions.hooks;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import cucumber.api.java.Before;
import stepDefintions.World;


public class Hooks {
	private World world;

    public Hooks(World world) {
        this.world = world;
    }
	
	@Before(order=0)
	public void doSetupBeforeExecution() {
		Properties properties = new Properties();
		try {
			String propertiesPath =System.getProperty("user.dir") + "\\src\\test\\resources\\config\\dev.properties";
			FileInputStream fis = new FileInputStream(propertiesPath);
			properties.load(fis);
		}catch (Exception e) { 
			System.out.println("exception while reading properties file. " + e.getMessage());
		}
		Map<String, String> map= new HashMap<>();
		for (Entry<Object, Object> entry : properties.entrySet()) {
		    map.put((String) entry.getKey(), (String) entry.getValue());
		}
		this.world.add2Context("config", map);
	}	


}
