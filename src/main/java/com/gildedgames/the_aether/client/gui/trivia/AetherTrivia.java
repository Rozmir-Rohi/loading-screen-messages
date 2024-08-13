package com.gildedgames.the_aether.client.gui.trivia;

import java.util.Random;

import loading_screen_messages.client.gui.loading_screen_message.LoadingScreenMessage;

public class AetherTrivia {
   private static Random random = new Random();

   public static String getNewTrivia()
   {
		return LoadingScreenMessage.getNewMessageForLoadingScreen();
   }
}