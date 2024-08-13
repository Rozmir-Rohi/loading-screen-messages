package loading_screen_messages.client.gui;

import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.Framebuffer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import loading_screen_messages.client.gui.message.MessageForLoadingScreen;

public class LoadingScreen extends LoadingScreenRenderer {

	private String message = "";

	private Minecraft minecraft;

	private String currentDisplayedMessage = "";

	private long systemTime = Minecraft.getSystemTime();

	private Framebuffer frameBuffer;

	public LoadingScreen(Minecraft minecraftInitial)
	{
		super(minecraftInitial);

		this.minecraft = minecraftInitial;

		this.frameBuffer = new Framebuffer(minecraftInitial.displayWidth, minecraftInitial.displayHeight, false);
		this.frameBuffer.setFramebufferFilter(9728);
	}

	@Override
	public void resetProgressAndMessage(String message)
	{
		super.resetProgressAndMessage(message);

		this.currentDisplayedMessage = MessageForLoadingScreen.getNewMessageForLoadingScreen();
	}

	@Override
	public void displayProgressMessage(String message)
	{
		this.systemTime = 0L;
		this.message = message;
		this.setLoadingProgress(-1);
		this.systemTime = 0L;
	}

	@Override
	public void setLoadingProgress(int progress)
	{
		long j = Minecraft.getSystemTime();

		if (j - this.systemTime >= 100L)
		{
			this.systemTime = j;
			ScaledResolution scaledresolution = new ScaledResolution(this.minecraft, this.minecraft.displayWidth, this.minecraft.displayHeight);
			int scaleFactor = scaledresolution.getScaleFactor();
			int scaledWidth = scaledresolution.getScaledWidth();
			int scaledHeight = scaledresolution.getScaledHeight();

			if (OpenGlHelper.isFramebufferEnabled())
			{
				this.frameBuffer.framebufferClear();
			}
			else
			{
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
			}

			this.frameBuffer.bindFramebuffer(false);
			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glOrtho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, 0.0F, -200.0F);

			if (!OpenGlHelper.isFramebufferEnabled())
			{
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			}

			if (!FMLClientHandler.instance().handleLoadingScreen(scaledresolution))
			{
				Tessellator tessellator = Tessellator.instance;
				this.minecraft.getTextureManager().bindTexture(Gui.optionsBackground);
				float factor = 32.0F;
				tessellator.startDrawingQuads();
				tessellator.setColorOpaque_I(4210752);
				tessellator.addVertexWithUV(0.0D, (double) scaledHeight, 0.0D, 0.0D, (double) ((float) scaledHeight / factor));
				tessellator.addVertexWithUV((double) scaledWidth, (double) scaledHeight, 0.0D, (double) ((float) scaledWidth / factor), (double) ((float) scaledHeight / factor));
				tessellator.addVertexWithUV((double) scaledWidth, 0.0D, 0.0D, (double) ((float) scaledWidth / factor), 0.0D);
				tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
				tessellator.draw();

				if (progress >= 0)
				{
					byte b0 = 100;
					byte b1 = 2;
					int j1 = scaledWidth / 2 - b0 / 2;
					int k1 = scaledHeight / 2 + 16;
					GL11.glDisable(GL11.GL_TEXTURE_2D);
					tessellator.startDrawingQuads();
					tessellator.setColorOpaque_I(8421504);
					tessellator.addVertex((double) j1, (double) k1, 0.0D);
					tessellator.addVertex((double) j1, (double) (k1 + b1), 0.0D);
					tessellator.addVertex((double) (j1 + b0), (double) (k1 + b1), 0.0D);
					tessellator.addVertex((double) (j1 + b0), (double) k1, 0.0D);
					tessellator.setColorOpaque_I(8454016);
					tessellator.addVertex((double) j1, (double) k1, 0.0D);
					tessellator.addVertex((double) j1, (double) (k1 + b1), 0.0D);
					tessellator.addVertex((double) (j1 + progress), (double) (k1 + b1), 0.0D);
					tessellator.addVertex((double) (j1 + progress), (double) k1, 0.0D);
					tessellator.draw();
					GL11.glEnable(GL11.GL_TEXTURE_2D);
				}

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				this.minecraft.fontRenderer.drawStringWithShadow(this.message, (scaledWidth - this.minecraft.fontRenderer.getStringWidth(this.message)) / 2, scaledHeight / 2 - 4 + 8, 16777215);
				this.minecraft.fontRenderer.drawStringWithShadow(this.currentDisplayedMessage, (scaledWidth - this.minecraft.fontRenderer.getStringWidth(this.currentDisplayedMessage)) / 2, scaledHeight - 16, 0xffff99);
			}

			this.frameBuffer.unbindFramebuffer();

			if (OpenGlHelper.isFramebufferEnabled())
			{
				this.frameBuffer.framebufferRender(scaledWidth * scaleFactor, scaledHeight * scaleFactor);
			}

			this.minecraft.func_147120_f();

			try
			{
				Thread.yield();
			}
			catch (Exception exception)
			{
				;
			}
		}
	}

}