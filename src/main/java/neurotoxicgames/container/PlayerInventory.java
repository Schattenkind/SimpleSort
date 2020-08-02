package neurotoxicgames.container;

import java.util.List;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;

public class PlayerInventory extends BaseContainer {

	public PlayerInventory(Container container) {
		super(container);
	}

	@Override
	public List<Slot> getStorageSlots() {
		return this.getContainer().inventorySlots.subList(9, 36);
	}

	@Override
	public List<Slot> getHotbarSlots() {
		return this.getContainer().inventorySlots.subList(36, 45);
	}

	@Override
	public List<Slot> getCraftingOutputSlots() {
		return this.getContainer().inventorySlots.subList(0, 1);
	}

	@Override
	public List<Slot> getCraftingInputSlots() {
		return this.getContainer().inventorySlots.subList(1, 5);
	}

	@Override
	public List<Slot> getArmorSlots() {
		return this.getContainer().inventorySlots.subList(5, 9);
	}

	@Override
	public List<Slot> getInputSlots() {
		return null;
	}

	@Override
	public List<Slot> getOutputSlots() {
		return null;
	}

	@Override
	public Slot getOffHandSlot() {
		return null;
	}

}
