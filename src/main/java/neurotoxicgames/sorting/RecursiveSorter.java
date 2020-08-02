package neurotoxicgames.sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.inventory.container.Slot;
import neurotoxicgames.api.IContainer;

public class RecursiveSorter extends BaseSorter{

	private List<Integer> unsortedList;
	private List<Integer> preSortedList;
	
	public RecursiveSorter(IContainer container) {
		super(container);
		this.unsortedList = getItemIdList();
		this.preSortedList = getPreSortById(this.unsortedList);
	}
	
	private List<Integer> getItemIdList(){
		List<Integer> itemIdList = new ArrayList<Integer>();
		for (Slot slot: slots) {
			Integer itemId = getItemIdFromSlot(slot);
			if (itemId != 0)
				itemIdList.add(itemId);
		}
		return itemIdList;
	}
	
	private List<Integer> getPreSortById(List<Integer> sortList) {
		List<Integer> sortedList = new ArrayList<Integer>(sortList);
		Collections.sort(sortedList); 
		return sortedList;
	}
	
	@Override
	public void sort() {
		this.recursiveSort(0, preSortedList);
	}
	
	public void recursiveSort(int startIndex, List<Integer> preSortedList) {
		if (startIndex >= slots.size())
			return;
		
		Slot startSlot = slots.get(startIndex);

		if (isEmptySlot(startSlot)) {
			recursiveSort(startIndex + 1, preSortedList);
			return;
		}
		
		Integer itemId = getItemIdFromSlot(startSlot);
		List<Slot> goalSlots = getAllSlotsForItem(itemId);
		
		leftClick(startSlot);
		for (Slot goalSlot: goalSlots) {
			if (isEmptySlot(goalSlot) || hasSameItem(startSlot, goalSlot))
				leftClick(goalSlot);
			else {
				leftClick(goalSlot);
			}
		}
		recursiveSort(startIndex + 1, preSortedList);		
	}
	
	public List<Slot> getAllSlotsForItem(Integer itemId){
		List<Slot> slotList = new ArrayList<Slot>();
		for (int i = 0; i < preSortedList.size(); i++) {
			if (preSortedList.get(i) == itemId) {
				slotList.add(slots.get(i));
			}
		}
		return slotList;
	}
}
