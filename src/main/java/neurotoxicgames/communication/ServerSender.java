package neurotoxicgames.communication;

import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;

public class ServerSender {
	
    public static void slotClick(PlayerController playerController, int windowId, int slot, int data, ClickType action, PlayerEntity player) {
        playerController.windowClick(windowId, slot, data, action, player);
    }

}
