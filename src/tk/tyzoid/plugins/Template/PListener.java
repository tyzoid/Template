package tk.tyzoid.plugins.Template;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class PListener extends PlayerListener {
	private final Template plugin;
	private String pluginname;
	
	PListener(Template instance){
		plugin = instance;
		pluginname = plugin.pluginname;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	String[] split = event.getMessage().split(" ");
    	String command = event.getMessage();
		Player player = event.getPlayer();
		
		//the /foo command
		
		
	}
}
