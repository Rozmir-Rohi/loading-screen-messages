package loading_screen_messages.client;


import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import loading_screen_messages.client.gui.LoadingScreen;

public class ClientEvents {

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) throws Exception {
		Minecraft minecraft = Minecraft.getMinecraft();
		TickEvent.Phase phase = event.phase;
		TickEvent.Type type = event.type;

		if (phase == TickEvent.Phase.END) {
			if (type.equals(TickEvent.Type.CLIENT))
			{
				if (!(minecraft.loadingScreen instanceof LoadingScreen))
				{
					minecraft.loadingScreen = new LoadingScreen(minecraft);
				}
			}
		}
	}
}