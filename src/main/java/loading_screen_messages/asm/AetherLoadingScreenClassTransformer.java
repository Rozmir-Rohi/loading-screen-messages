package loading_screen_messages.asm;


import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.launchwrapper.IClassTransformer;

public class AetherLoadingScreenClassTransformer implements IClassTransformer {
   public byte[] transform(String name, String transformedName, byte[] classBeingTransformed) 
   {
	   if (name.equals("com.gildedgames.the_aether.client.trivia.AetherTrivia")) //this forces the Aether mod to use the messages from this mod if it is installed
	   {
		   System.out.println("[Loading Screen Messages]: Currently inside the transformer and about to patch: " + name);
		   classBeingTransformed = patchClassInJar(name, classBeingTransformed, name, AetherLoadingScreenFMLLoadingPlugin.location);
	   }
	   return classBeingTransformed;
   }

   public byte[] patchClassInJar(String name, byte[] bytes, String obfuscatedName, File location)
   {
	      try 
	      {
	         ZipFile zipFile = new ZipFile(location);
	         ZipEntry entry = zipFile.getEntry(name.replace('.', '/') + ".class");
	         if (entry == null) 
	         {
	            System.out.println("[Loading Screen Messages]: " + name + " not found in " + location.getName());
	         }
	         else
	         {
	            InputStream zin = zipFile.getInputStream(entry);
	            bytes = new byte[(int)entry.getSize()];
	            zin.read(bytes);
	            zin.close();
	            System.out.println("[Loading Screen Messages]: Class " + name + " has been patched!");
	         }
	
	         zipFile.close();
	         return bytes;
	      }
	      catch (Exception var8) 
	      {
	         throw new RuntimeException("[Loading Screen Messages]: Error overriding " + name + " from " + location.getName(), var8);
	      }
   }
}