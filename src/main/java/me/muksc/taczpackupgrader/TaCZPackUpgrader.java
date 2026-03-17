package me.muksc.taczpackupgrader;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class TaCZPackUpgrader implements ModInitializer {
    public static final String MOD_ID = "taczpackupgrader";

    @Override
    public void onInitialize() {
        Upgrader.INSTANCE.run(FabricLoader.getInstance().getGameDir().resolve("tacz"));
    }
}
