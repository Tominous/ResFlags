package me.kangarko.ResFlags;

import me.kangarko.ResFlags.Flags.AnimalKilling;
import me.kangarko.ResFlags.Flags.Cake;
import me.kangarko.ResFlags.Flags.Commands;
import me.kangarko.ResFlags.Flags.Drops;
import me.kangarko.ResFlags.Flags.EnderPearl;
import me.kangarko.ResFlags.Flags.Form;
import me.kangarko.ResFlags.Flags.Melt;
import me.kangarko.ResFlags.Flags.MobKilling;
import me.kangarko.ResFlags.Flags.Pickup;
import me.kangarko.ResFlags.Flags.Sapling;
import me.kangarko.ResFlags.Flags.Shear;
import me.kangarko.ResFlags.Flags.Trade;
import me.kangarko.ResFlags.Flags.VehicleProtect;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bekvon.bukkit.residence.protection.FlagPermissions;

public class ResFlags extends JavaPlugin {
	private static FileConfiguration config;

	@Override
	public void onEnable() {
		if (getServer().getPluginManager().getPlugin("Residence") == null)
			throw new NullPointerException("Failed to find Residence plugin!");
		config = getConfig();

		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

		load();
	}

	private void load() {
		PluginManager pm = getServer().getPluginManager();

		if (getConfig().getBoolean("melt")) {
			FlagPermissions.addResidenceOnlyFlag("melt");
			pm.registerEvents(new Melt(), this);
		}
		if (getConfig().getBoolean("form")) {
			FlagPermissions.addResidenceOnlyFlag("form");
			pm.registerEvents(new Form(), this);
		}
		if (getConfig().getBoolean("drops")) {
			FlagPermissions.addFlag("drops");
			pm.registerEvents(new Drops(), this);
		}
		if (getConfig().getBoolean("pickup")) {
			FlagPermissions.addFlag("pickup");
			pm.registerEvents(new Pickup(), this);
		}
		if (getConfig().getBoolean("animalkilling")) {
			FlagPermissions.addFlag("animalkilling");
			pm.registerEvents(new AnimalKilling(), this);
		}
		if (getConfig().getBoolean("mobkilling")) {
			FlagPermissions.addFlag("mobkilling");
			pm.registerEvents(new MobKilling(), this);
		}
		if (getConfig().getBoolean("enderpearl")) {
			FlagPermissions.addFlag("enderpearl");
			pm.registerEvents(new EnderPearl(), this);
		}
		if (getConfig().getBoolean("commands")) {
			FlagPermissions.addFlag("commands");
			pm.registerEvents(new Commands(), this);
		}
		if (getConfig().getBoolean("vehicleprotect")) {
			FlagPermissions.addFlag("vehicleprotect");
			pm.registerEvents(new VehicleProtect(), this);
		}
		if (getConfig().getBoolean("shear")) {
			FlagPermissions.addFlag("shear");
			pm.registerEvents(new Shear(), this);
		}
		if (getConfig().getBoolean("trade")) {
			FlagPermissions.addFlag("trade");
			pm.registerEvents(new Trade(), this);
		}

		FlagPermissions.addFlag("sapling");
		pm.registerEvents(new Sapling(), this);
		pm.registerEvents(new Cake(), this);
	}

	public static void sendMsg(Player pl, String str) {
		String path = "messages." + str;
		if (config.getString(path) == null || config.getString(path).equalsIgnoreCase("none"))
			return;
		pl.sendMessage(colorize(config.getString(path).replace("%prefix", config.getString("prefix"))));
	}

	private static String colorize(String str) {
		return str.replace("&", "\u00A7");
	}
}