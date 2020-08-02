package neurotoxicgames;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import neurotoxicgames.api.IContainer;
import neurotoxicgames.api.ISorter;
import neurotoxicgames.container.PlayerInventory;
import neurotoxicgames.sorting.SlotBySlotSorter;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;

public class Main {
	public static Minecraft MC;
	
	public static void initialize() {
		MC = Minecraft.getInstance();
		
		SimpleSort.LOGGER.debug("Initialized.");
	}

	public static void onGuiOpen(Screen gui) {
		if (gui instanceof ContainerScreen) {
//			ContainerScreen<?> container = (ContainerScreen<?>) gui;
//			currentInventorySlots = container.getContainer().inventorySlots;
//		} else if (gui == null) {
//			currentInventorySlots = null;
		}
	}
	
	public static void onSortKeyPressed() {
		SimpleSort.LOGGER.debug("sort key pressed");
		IContainer container = new PlayerInventory(MC.player.openContainer);
		ISorter sorter = new SlotBySlotSorter(container);
		sorter.sort();
	}	
}
