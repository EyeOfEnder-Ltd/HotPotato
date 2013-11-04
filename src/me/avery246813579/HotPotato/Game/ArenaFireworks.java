package me.avery246813579.HotPotato.Game;

import java.lang.reflect.Method;
import java.util.Random;

import me.avery246813579.HotPotato.HotPotato;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class ArenaFireworks {

	@SuppressWarnings("unused")
	private HotPotato plugin;
	
	public ArenaFireworks(HotPotato plugin){
		this.plugin = plugin;
	}
	
	// internal references, performance improvements
		private Method world_getHandle = null;
		private Method nms_world_broadcastEntityEffect = null;
		private Method firework_getHandle = null;

		/**
		 * Play a pretty firework at the location with the FireworkEffect when
		 * called
		 * 
		 * @param world
		 * @param loc
		 * @param fe
		 * @throws Exception
		 */
		public void playFirework(World world, Location loc, FireworkEffect fe)
				throws Exception {
			// Bukkity load (CraftFirework)
			Firework fw = world.spawn(loc, Firework.class);
			// the net.minecraft.server.World
			Object nms_world = null;
			Object nms_firework = null;
			/*
			 * The reflection part, this gives us access to funky ways of messing
			 * around with things
			 */
			if (world_getHandle == null) {
				// get the methods of the craftbukkit objects
				world_getHandle = getMethod(world.getClass(), "getHandle");
				firework_getHandle = getMethod(fw.getClass(), "getHandle");
			}
			// invoke with no arguments
			nms_world = world_getHandle.invoke(world, (Object[]) null);
			nms_firework = firework_getHandle.invoke(fw, (Object[]) null);
			// null checks are fast, so having this seperate is ok
			if (nms_world_broadcastEntityEffect == null) {
				// get the method of the nms_world
				nms_world_broadcastEntityEffect = getMethod(nms_world.getClass(),
						"broadcastEntityEffect");
			}
			/*
			 * Now we mess with the metadata, allowing nice clean spawning of a
			 * pretty firework (look, pretty lights!)
			 */
			// metadata load
			FireworkMeta data = fw.getFireworkMeta();
			// clear existing
			data.clearEffects();
			// power of one
			data.setPower(1);
			// add the effect
			data.addEffect(fe);
			// set the meta
			fw.setFireworkMeta(data);
			/*
			 * Finally, we broadcast the entity effect then kill our fireworks
			 * object
			 */
			// invoke with arguments
			nms_world_broadcastEntityEffect.invoke(nms_world, new Object[] {
					nms_firework, (byte) 17 });
			// remove from the game
			fw.remove();
		}

		private static Method getMethod(Class<?> cl, String method) {
			for (Method m : cl.getMethods()) {
				if (m.getName().equals(method)) {
					return m;
				}
			}
			return null;
		}

		public static FireworkEffect getRandomEffect() {
			Random generator = new Random();
			int type = generator.nextInt(9) + 1;
			if (type == 1) {
				return FireworkEffect.builder().with(Type.BALL)
						.withColor(Color.RED).withFade(Color.BLUE).build();
			} else if (type == 2) {
				return FireworkEffect.builder().with(Type.BALL)
						.withColor(Color.BLUE).withFade(Color.GREEN).build();
			} else if (type == 3) {
				return FireworkEffect.builder().with(Type.BALL)
						.withColor(Color.GREEN).withFade(Color.RED).build();
			} else if (type == 4) {
				return FireworkEffect.builder().with(Type.BURST)
						.withColor(Color.RED).withFade(Color.GREEN).build();
			} else if (type == 5) {
				return FireworkEffect.builder().with(Type.BURST)
						.withColor(Color.BLUE).withFade(Color.RED).build();
			} else if (type == 6) {
				return FireworkEffect.builder().with(Type.BURST)
						.withColor(Color.GREEN).withFade(Color.BLUE).build();
			} else if (type == 7) {
				return FireworkEffect.builder().with(Type.STAR)
						.withColor(Color.RED).withFade(Color.BLUE).build();
			} else if (type == 8) {
				return FireworkEffect.builder().with(Type.STAR)
						.withColor(Color.BLUE).withFade(Color.GREEN).build();
			} else if (type == 9) {
				return FireworkEffect.builder().with(Type.STAR)
						.withColor(Color.GREEN).withFade(Color.RED).build();
			} else {
				return FireworkEffect.builder().with(Type.STAR)
						.withColor(Color.BLUE).withFade(Color.RED).build();
			}
		}

		public FireworkEffect getBlowupRandomEffect() {
			Random generator = new Random();
			int type = generator.nextInt(6) + 1;
			if (type == 1) {
				return FireworkEffect.builder().with(Type.BALL_LARGE)
						.withColor(Color.RED).withFade(Color.GREEN).build();
			} else if (type == 2) {
				return FireworkEffect.builder().with(Type.BALL_LARGE)
						.withColor(Color.BLUE).withFade(Color.RED).build();
			} else if (type == 3) {
				return FireworkEffect.builder().with(Type.BALL_LARGE)
						.withColor(Color.GREEN).withFade(Color.BLUE).build();
			} else if (type == 4) {
				return FireworkEffect.builder().with(Type.CREEPER)
						.withColor(Color.RED).withFade(Color.BLUE).build();
			} else if (type == 5) {
				return FireworkEffect.builder().with(Type.CREEPER)
						.withColor(Color.BLUE).withFade(Color.GREEN).build();
			} else if (type == 6) {
				return FireworkEffect.builder().with(Type.CREEPER)
						.withColor(Color.GREEN).withFade(Color.RED).build();
			} else {
				return FireworkEffect.builder().with(Type.CREEPER)
						.withColor(Color.BLUE).withFade(Color.RED).build();
			}
		}

}
