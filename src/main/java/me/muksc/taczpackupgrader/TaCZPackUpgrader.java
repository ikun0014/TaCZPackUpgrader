package me.muksc.taczpackupgrader;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(TaCZPackUpgrader.MOD_ID)
public class TaCZPackUpgrader {
    public static final String MOD_ID = "taczpackupgrader";

    public static ModContainer container;

    public TaCZPackUpgrader(IEventBus bus, ModContainer container) {
        TaCZPackUpgrader.container = container;

        Upgrader.INSTANCE.run(FMLPaths.GAMEDIR.get().resolve("tacz"));
    }
}
