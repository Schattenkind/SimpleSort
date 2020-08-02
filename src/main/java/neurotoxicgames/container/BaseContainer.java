package neurotoxicgames.container;

import java.util.List;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import neurotoxicgames.api.IContainer;

public abstract class BaseContainer implements IContainer {
	
	private Container container;
	
	public BaseContainer(Container container) {
		this.setContainer(container);
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	@Override
	public int getWindowId() {
		return this.getContainer().windowId;
	}

	// TODO make them not abstract and return null as default?
	@Override
	public abstract List<Slot> getStorageSlots();

	@Override
	public abstract List<Slot> getHotbarSlots();

	@Override
	public abstract List<Slot> getCraftingOutputSlots();

	@Override
	public abstract List<Slot> getCraftingInputSlots();

	@Override
	public abstract List<Slot> getArmorSlots();

	@Override
	public abstract List<Slot> getInputSlots();

	@Override
	public abstract List<Slot> getOutputSlots();

	@Override
	public abstract Slot getOffHandSlot();
}
