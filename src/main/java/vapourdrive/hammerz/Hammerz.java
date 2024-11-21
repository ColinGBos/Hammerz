package vapourdrive.hammerz;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vapourdrive.hammerz.config.ConfigSettings;
import vapourdrive.hammerz.setup.Registration;

@Mod(Hammerz.MODID)
public class Hammerz {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "hammerz";
    public static final boolean debugMode = true;

    public Hammerz(ModContainer container) {
        IEventBus eventBus = container.getEventBus();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigSettings.CLIENT_CONFIG);
        container.registerConfig(ModConfig.Type.SERVER, ConfigSettings.SERVER_CONFIG);
        container.registerConfig(ModConfig.Type.CLIENT, ConfigSettings.CLIENT_CONFIG);

        Registration.init(container.getEventBus());

        // Register the setup method for modloading
        assert eventBus != null;
        eventBus.addListener(Registration::buildContents);
    }

    public static void debugLog(String toLog) {
        if (isDebugMode()) {
            LOGGER.log(Level.DEBUG, toLog);
        }
    }

    public static boolean isDebugMode() {
        return java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("jdwp") && debugMode;
    }

}
