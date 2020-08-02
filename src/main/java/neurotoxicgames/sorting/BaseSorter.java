package neurotoxicgames.sorting;

import java.util.List;

import net.minecraft.inventory.container.ClickType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import neurotoxicgames.Main;
import neurotoxicgames.api.IContainer;
import neurotoxicgames.api.ISorter;
import neurotoxicgames.communication.ServerSender;

public abstract class BaseSorter implements ISorter {
	
	IContainer container;
	List<Slot> slots;
	List<Integer> unsortedList;
	List<Integer> preSortedList;
	
	public BaseSorter(IContainer container) {
		this.container = container;
		this.slots = container.getStorageSlots();
	}
	
	public abstract void sort();
	
	public boolean slotsCanBeMergedCompletely(Slot firstSlot, Slot secondSlot) {
		if (!hasSameItem(firstSlot, secondSlot)) {
			return false;
		}
		
		return (firstSlot.getStack().getCount() + secondSlot.getStack().getCount() <= firstSlot.getStack().getMaxStackSize());
	}

	public boolean isEmptySlot(Slot slot) {
		return getItemIdFromSlot(slot) == 0;
	}

	public boolean hasSameItem(Slot firstSlot, Slot secondSlot) {
		return getItemIdFromSlot(firstSlot) == getItemIdFromSlot(secondSlot);
	}

	public void move(Slot from, Slot to) {
		leftClick(from);
		leftClick(to);
	}

	public void rightClick(Slot slot) {
		click(slot, true);
	}

	public void leftClick(Slot slot) {
		click(slot, false);
	}

	public void click(Slot slot, boolean rightClick) {
		int data = (rightClick) ? 1 : 0;
		ServerSender.slotClick(Main.MC.playerController, container.getWindowId(), slot.slotNumber, data,
				ClickType.PICKUP, Main.MC.player);
	}

	public int getItemIdFromSlot(Slot slot) {
		return Item.getIdFromItem(slot.getStack().getItem());
	}
}
