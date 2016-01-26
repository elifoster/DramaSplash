package io.github.elifoster.dramasplash;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Mod(name = "Drama Generator: Ruby Edition Splash", modid = "elifosterDramaSplash", version = "1.0.0")
public class DramaSplash {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(this);
    }

    private boolean isSplashSet = false;

    @SubscribeEvent
    public void render(TickEvent.RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        GuiScreen screen = minecraft.currentScreen;
        if (event.side.isClient() && screen != null && screen instanceof GuiMainMenu &&
          !isSplashSet) {
            GuiMainMenu menu = (GuiMainMenu) screen;
            ObfuscationReflectionHelper.setPrivateValue(GuiMainMenu.class, menu, getSplash(),
              "splashText", "field_73975_c", "r");
            isSplashSet = true;
        }
    }

    /**
     * Gets the splash from mc-drama's API
     * @return The random drama, or "Check your logs!" if there is an MalformedURLException or
     * IOException.
     */
    private String getSplash() {
        try {
            URL url = new URL("http://mc-drama.herokuapp.com/raw");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = in.readLine();
            in.close();
            return line;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Check your logs!";
    }
}
