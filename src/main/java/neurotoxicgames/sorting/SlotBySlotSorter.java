package neurotoxicgames.sorting;

import net.minecraft.inventory.container.Slot;
import neurotoxicgames.GUIHelper;
import neurotoxicgames.api.IContainer;

public class SlotBySlotSorter extends BaseSorter {

	private int highestSortedSlot;

	public SlotBySlotSorter(IContainer container) {
		super(container);
		highestSortedSlot = 0;
	}

	@Override
	public void sort() {
		for (Slot slot : slots) {
			if (sortSlot(slot))
				break;
		}
	}

	/**
	 * TODO
	 * 
	 * @param slot
	 * @return
	 */
	private boolean sortSlot(Slot slot) {
		Slot lowestItemIdSlot = getNextItemToSortSlot();
		// only empty slots remaining, we are done
		if (lowestItemIdSlot == null) {
			return true;
		}

		// put it in currently next slot to be sorted
		Slot currentSlot = slots.get(highestSortedSlot);
		putItemInSortedSlot(currentSlot, lowestItemIdSlot);

		highestSortedSlot++;
		return false;
	}

	/**
	 * TODO
	 * 
	 * @param currentSlot
	 * @param sourceSlot
	 */
	private void putItemInSortedSlot(Slot currentSlot, Slot lowestItemIdSlot) {
		Slot lastSlot = null;
		if (highestSortedSlot > 0)
			lastSlot = slots.get(highestSortedSlot - 1);
		
		boolean currentSlotIsEmpty = isEmptySlot(currentSlot);
		boolean currentSlotHasSameItem = hasSameItem(currentSlot, lowestItemIdSlot);
		boolean currentSlotsCanBeMergedCompletely = slotsCanBeMergedCompletely(currentSlot, lowestItemIdSlot);
		
		boolean lastSlotHasSameItem = lastSlot != null && hasSameItem(lastSlot, lowestItemIdSlot);
		boolean lastSlotCanBeMergedCompletely = lastSlot != null && slotsCanBeMergedCompletely(lastSlot, lowestItemIdSlot);
		
		// pickup lowest unsorted item
		leftClick(lowestItemIdSlot);		

		// check last slot if we still have the same item and can fill up last stack
		if (lastSlotHasSameItem) {
			leftClick(lastSlot);
			if (lastSlotCanBeMergedCompletely) {
				highestSortedSlot--;
				return;
			}
		}
	

		// just put it in the empty slot or with same item and we are done
		if (currentSlotIsEmpty || currentSlotsCanBeMergedCompletely) {
			leftClick(currentSlot);
			return;
		}
		// same item but can't take stack completely, put rest in next slot
		else if (currentSlotHasSameItem	&& !currentSlotsCanBeMergedCompletely) {
			leftClick(currentSlot);

			highestSortedSlot++;
			Slot nextSlot = slots.get(highestSortedSlot);

			leftClick(nextSlot);
		}
		// slot is already occupied, swap places
		else {
			leftClick(currentSlot);
			leftClick(lowestItemIdSlot);
		}
	}

	private Slot getNextItemToSortSlot() {
		int lowestItemId = Integer.MAX_VALUE;
		Slot lowestItemIdSlot = null;
		for (Slot slot : slots.subList(highestSortedSlot, slots.size())) {
			if (this.isEmptySlot(slot)) {
				continue;
			}
			int currentItemId = getItemIdFromSlot(slot);

			if (currentItemId <= lowestItemId) {
				lowestItemId = currentItemId;
				lowestItemIdSlot = slot;
			}
		}
		return lowestItemIdSlot;
	}
}
