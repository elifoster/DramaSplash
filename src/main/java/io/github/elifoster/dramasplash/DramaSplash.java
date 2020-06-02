package io.github.elifoster.dramasplash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Mod("drama_splash")
@Mod.EventBusSubscriber
public class DramaSplash {
    private static boolean isSplashSet = false;

    @SubscribeEvent
    public static void render(TickEvent.RenderTickEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        GuiScreen screen = minecraft.currentScreen;
        if (event.side == LogicalSide.CLIENT && screen instanceof GuiMainMenu && !isSplashSet) {
            GuiMainMenu menu = (GuiMainMenu) screen;
            ObfuscationReflectionHelper.setPrivateValue(GuiMainMenu.class, menu, getSplash(), "field_73975_c");
            isSplashSet = true;
        }
    }

    /**
     * Gets the splash from mc-drama's API
     * @return The random drama, or "Drama Generator fails to generate! [check your logs!]" if there is an IOException.
     */
    private static String getSplash() {
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
