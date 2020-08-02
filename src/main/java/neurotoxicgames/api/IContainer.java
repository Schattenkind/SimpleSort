package neurotoxicgames.api;

import java.util.List;

import net.minecraft.inventory.container.Slot;

/*https://c4k3.github.io/wiki.vg/Inventory.html*/
public interface IContainer {
	
	int getWindowId();
	
	List<Slot> getStorageSlots();
	
	List<Slot> getHotbarSlots();
	
	List<Slot> getCraftingOutputSlots();
	
	List<Slot> getCraftingInputSlots();
	
	List<Slot> getArmorSlots();
	
	List<Slot> getInputSlots();
	
	List<Slot> getOutputSlots();
	
	Slot getOffHandSlot();
}
