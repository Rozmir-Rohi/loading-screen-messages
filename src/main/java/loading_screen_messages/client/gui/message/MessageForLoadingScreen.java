package loading_screen_messages.client.gui.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

import com.google.common.collect.Lists;

import loading_screen_messages.LoadingScreenMessages;
import net.minecraft.client.resources.I18n;

public class MessageForLoadingScreen {

	private static Random random = new Random();

	public MessageForLoadingScreen() {

	}

	public static String getNewMessageForLoadingScreen()
	{
		String localization = Minecraft.getMinecraft().getLanguageManager().getCurrentLanguage().getLanguageCode();

		if (getEntriesForLocalization(localization) != null)
		{
			return getEntriesForLocalization(localization);
		}
		else if (getEntriesForLocalization("en_US") != null)
		{
			return getEntriesForLocalization("en_US");
		}
		else
		{
			return "missingno";
		}
	}

	public static String getEntriesForLocalization(String localization)
	{
		BufferedReader bufferedReader = null;

		try
		{
			List<String> list = Lists.newArrayList();
			bufferedReader = new BufferedReader(new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(LoadingScreenMessages.locate(localization + ".txt")).getInputStream(), StandardCharsets.UTF_8));
			String string;

			while ((string = bufferedReader.readLine()) != null)
			{
				string = string.trim();

				if (!string.isEmpty())
				{
					list.add(string);
				}
			}

			if (!list.isEmpty())
			{
				return I18n.format(list.get(random.nextInt(list.size())));
			}
		}
		catch (IOException ignore) { }
		finally
		{
			if (bufferedReader != null)
			{
				try
				{
					bufferedReader.close();
				}
				catch (IOException ignore) { }
			}
		}

		return null;
	}
}