package loading_screen_messages;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = LoadingScreenMessages.MOD_ID, version = "v1.0")
public class LoadingScreenMessages {

	public static final String MOD_ID = "loading_screen_messages";

	@Instance(LoadingScreenMessages.MOD_ID)
	public static LoadingScreenMessages instance;

	@SidedProxy(clientSide = "loading_screen_messages.client.ClientProxy", serverSide = "loading_screen_messages.CommonProxy")
	public static CommonProxy proxy;
	
	public static boolean isAetherLegacyLoaded;

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		isAetherLegacyLoaded = Loader.isModLoaded("aether_legacy");
		
		
		proxy.init();
	}

	public static ResourceLocation locate(String location)
	{
		return new ResourceLocation(MOD_ID, location);
	}

	public static String find(String location)
	{
		return modAddress() + location;
	}

	public static String modAddress()
	{
		return MOD_ID + ":";
	}
}