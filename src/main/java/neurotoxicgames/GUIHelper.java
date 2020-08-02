package neurotoxicgames;

import net.minecraft.util.text.StringTextComponent;

public class GUIHelper {
	
	public static void logIngameChat(String message) {
        if(Main.MC.ingameGUI != null) {
        	Main.MC.ingameGUI.getChatGUI().printChatMessage(new StringTextComponent(message));
        }
	}
}
