package loading_screen_messages.client;
import cpw.mods.fml.client.FMLClientHandler;
import loading_screen_messages.CommonProxy;
import loading_screen_messages.LoadingScreenMessages;
import loading_screen_messages.client.gui.LoadingScreen;



public class ClientProxy extends CommonProxy {
	@Override
	public void init()
	{
		if (!LoadingScreenMessages.isAetherLegacyLoaded) //don't try to register loading screen message event if the Aether mod is loaded
		{
			registerEvent(new ClientEvents());
		}
	}
}