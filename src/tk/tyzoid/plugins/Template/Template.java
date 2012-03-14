package tk.tyzoid.plugins.Template;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tk.tyzoid.plugins.Template.PListener;

public class Template extends JavaPlugin {
	String pluginname = "Template";
	
    private final PListener playerListener = new PListener(this);
    public Settings settings = new Settings(this);
    public boolean permissionsExists = false;
    public boolean useSuperperms = false;

    public void onDisable() {
        System.out.println("[" + pluginname +"] " + pluginname + " is closing...");
    }

    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println("[" + pluginname + "] Starting " + pluginname + " v" + pdfFile.getVersion() + "...");
        settings.readSettings();
    }
    
    /* Valid nodes:
     * template.*
     * template.foo
     * template.bar
     */
}