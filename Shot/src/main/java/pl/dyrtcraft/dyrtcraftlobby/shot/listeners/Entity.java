package pl.dyrtcraft.dyrtcraftlobby.shot.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import pl.dyrtcraft.dyrtcraftlobby.shot.DyrtCraftLobbyShot;
//import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class Entity implements Listener {
	
	DyrtCraftLobbyShot plugin;
	
	public Entity(DyrtCraftLobbyShot dyrtCraftLobbyShot) {
		plugin = dyrtCraftLobbyShot;
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onCreateSpawnEvent(CreatureSpawnEvent e) {
		e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 7));
	}
	
	@EventHandler
	public void onEntityTarget(EntityTargetEvent e) {
		e.setCancelled(true);
	}
	
}
