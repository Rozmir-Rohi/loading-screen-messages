package com.gildedgames.the_aether.client.gui.trivia;

import java.util.Random;

import loading_screen_messages.client.gui.message.MessageForLoadingScreen;

public class AetherTrivia {
   private static Random random = new Random();

   public static String getNewTrivia()
   {
		return MessageForLoadingScreen.getNewMessageForLoadingScreen();
   }
}