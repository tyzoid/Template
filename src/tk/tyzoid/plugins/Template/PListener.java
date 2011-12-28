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
		if(split[0] == "/foo" && plugin.hasPermission(player, "template.foo")){
			player.sendMessage("[" + pluginname + "] I sense shenanigans. You used /foo");
			plugin.getServer().broadcastMessage("[" + pluginname + "] " + player.getName() + " used /foo");
			event.setCancelled(true);
			
			return;
		}
		
		if(split[0] == "/bar" && plugin.hasPermission(player, "template.bar")){
			player.sendMessage("[" + pluginname + "] I sense shenanigans. You used /bar");
			plugin.getServer().broadcastMessage("[" + pluginname + "] " + player.getName() + " used /bar");
			event.setCancelled(true);
			
			return;
		}
	}
}
