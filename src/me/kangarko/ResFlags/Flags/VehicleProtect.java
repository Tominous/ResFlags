package me.kangarko.ResFlags.Flags;

import me.kangarko.ResFlags.ResFlags;

import org.bukkit.Location;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;

public class VehicleProtect implements Listener {
	@EventHandler(ignoreCancelled = true)
	public void onVehicleDestroy(VehicleDestroyEvent e) {
		try {
			Location loc = e.getVehicle().getLocation();
			Entity attacker = e.getAttacker();
			Vehicle vehicle = e.getVehicle();

			Player player = (Player) attacker;
			ClaimedResidence res = Residence.getResidenceManager().getByLoc(loc);
			boolean resadmin = Residence.isResAdminOn(player);

			if (res != null && res.getPermissions().playerHas(player.getName(), "vehicleprotect", false) && !resadmin && (vehicle instanceof Minecart || vehicle instanceof Boat) && (attacker instanceof Player || attacker instanceof Monster)) {
				ResFlags.sendMsg(player, "vehicledestroy");
				e.setCancelled(true);
			}
		} catch (Exception localException) {
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onVehicleDestroyEntity(VehicleDestroyEvent e) {
		Location loc = e.getVehicle().getLocation();
		ClaimedResidence res = Residence.getResidenceManager().getByLoc(loc);

		if (res != null && res.getPermissions().has("vehicleprotect", false) && (e.getVehicle() instanceof Minecart || e.getVehicle() instanceof Boat) && e.getAttacker() instanceof Monster) {
			e.setCancelled(true);
		}
	}
}