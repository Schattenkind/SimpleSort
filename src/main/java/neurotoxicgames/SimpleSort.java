package neurotoxicgames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

/**
 * @author Schattenkind
 */
@Mod(SimpleSort.MODID)
public class SimpleSort {
	public static final String MODID = "simplesort";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	private static ArrayList<KeyBinding> keyBindings;
	private static Map<KeyBinding, Long> lastPressedKeys;

	public SimpleSort() {
		if (FMLEnvironment.dist != Dist.CLIENT) {
			LOGGER.debug("Disabled because not running on the client.");
			return;
		}

		lastPressedKeys = new HashMap<KeyBinding, Long>();
		keyBindings = new ArrayList<KeyBinding>();
		
		registerHotkeys();
		Main.initialize();
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
	    // Send when a gui is opened or closed (with null in getGui() in the latter case).
		LOGGER.debug("onGuiOpen gui = " + event.getGui());
		Main.onGuiOpen(event.getGui());
	}
	
	
	@SubscribeEvent
	public void onTick(ClientTickEvent event) {
		for(KeyBinding b: keyBindings) {
			if (b.isKeyDown()) {
				if (keyWasntPressedWithinDelay(b, 500)) {
					GUIHelper.logIngameChat("Key " + b.getKeyDescription() + " was pressed");
					Main.onSortKeyPressed();
				}
			}
		}
	}
	
	private boolean keyWasntPressedWithinDelay(KeyBinding key, long delay) {
		long lastPressed = lastPressedKeys.put(key, System.currentTimeMillis());
		return (System.currentTimeMillis() - lastPressed  > delay);
	}
	
	private void registerHotkeys() {		
		//setup keybindings
		KeyBinding sortKey = new KeyBinding("key.sorterkey", GLFW.GLFW_KEY_R, "key.sorterkey.category");
		
		//add keybindings
		keyBindings.add(sortKey);
		
		//initialize last pressed map
		lastPressedKeys.put(sortKey, 0L);
		
		ClientRegistry.registerKeyBinding(sortKey);
	}
}
