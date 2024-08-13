package loading_screen_messages.client;
import cpw.mods.fml.common.FMLCommonHandler;
import loading_screen_messages.CommonProxy;
import loading_screen_messages.LoadingScreenMessages;
import net.minecraftforge.common.MinecraftForge;



public class ClientProxy extends CommonProxy {
	@Override
	public void init()
	{
		if (!LoadingScreenMessages.isAetherLegacyLoaded) //don't try to register loading screen message event if the Aether mod is loaded.
		{
			registerEvent(new ClientEvents());
		}
	}
	
	public static void registerEvent(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
}