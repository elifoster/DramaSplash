package io.github.elifoster.dramasplash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Mod(name = "Drama Generator: Ruby Edition Splash", modid = "drama_splash", version = "2.0.3", acceptedMinecraftVersions = "[1.8,1.12.2]")
public class DramaSplash {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
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
     * @return The random drama, or "Drama Generator fails to generate! [check your logs!]" if there is an IOException.
     */
    private String getSplash() {
        try {
            URL url = new URL("http://mc-drama.herokuapp.com/raw");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = in.readLine();
            in.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Drama Generator fails to generate! [check your logs!]";
    }
}
