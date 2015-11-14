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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * Gets the splash from mc-drama.herokuapp.com
     * If it errors, or for some reason cannot find the drama, it will use a default "Check your
     * logs!" message.
     * @return
     */
    private String getSplash() {
        try {
            URL url = new URL("http://mc-drama.herokuapp.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            Pattern pattern = Pattern.compile("<h2> (.*?)</h2>");
            while ((line = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    return matcher.group(1);
                }
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Check your logs!";
    }
}
