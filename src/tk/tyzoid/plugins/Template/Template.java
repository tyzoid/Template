package tk.tyzoid.plugins.Template;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.tyzoid.plugins.Template.PListener;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Template extends JavaPlugin {
	String pluginname = "Template";
	
    private final PListener playerListener = new PListener(this);
    public settings cSettings = new settings();
    public PermissionHandler permissionHandler;
    public boolean permissionsExists = false;
    public boolean useSuperperms = false;

    public void onDisable() {
        System.out.println("[" + pluginname +"] " + pluginname + " is closing...");
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Priority.Highest, this);
        
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[" + pluginname + "] Starting " + pluginname + " v" + pdfFile.getVersion() + "...");
        setupPermissions();
        cSettings.readSettings();
    }
    
    private void setupPermissions() {
        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if (permissionHandler == null) {
            if (permissionsPlugin != null) {
            	permissionsExists = true;
                permissionHandler = ((Permissions) permissionsPlugin).getHandler();
                System.out.println("[" + pluginname + "] Permissions found!");
            } else {
                permissionsExists = false;
                
                try{
                	@SuppressWarnings("unused")
					Permission fakePerm = new Permission("fake.perm");
                	useSuperperms = true;
                    System.out.println("[" + pluginname + "] Using built-in permissions.");
                } catch(Exception e){
                	//superperms doesn't exist
                    System.out.println("[" + pluginname + "] Update CraftBukkit, will ya?");
                }
            }
        }
    }
    
    /* Valid nodes:
     * template.*
     * template.foo
     * template.bar
     */
    public boolean hasPermission(Player p, String node){
    	if(!useSuperperms){
    		return permissionHandler.has(p, node);
    	} else {
    		return p.hasPermission(node);
    	}
    }
}