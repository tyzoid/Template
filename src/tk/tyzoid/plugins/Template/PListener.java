package tk.tyzoid.plugins.Template;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PListener implements Listener {
	private final Template plugin;
	private String pluginname;
	
	PListener(Template instance){
		plugin = instance;
		pluginname = plugin.pluginname;
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
    	String[] split = event.getMessage().split(" ");
    	//String command = event.getMessage();
		Player player = event.getPlayer();
		
		//the /foo command
		if(split[0] == "/checkID" && player.hasPermission("template.foo")){
			player.sendMessage("[" + pluginname + "] I sense shenanigans. You used /foo");
			plugin.getServer().broadcastMessage("[" + pluginname + "] " + player.getName() + " used /foo");
			event.setCancelled(true);
			
			return;
		}
	}
}
