package ml.pkom.lunachat;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class LunaChat implements ModInitializer {

    public static final String MOD_ID = "lunachat";
    public static final String MOD_NAME = "LunaChat";

    public static LunaChat instance;
    private static Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
        log(Level.INFO, "Initializing");
        instance = this;
	}

    public static void log(Level level, String message){
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    public static Identifier id(String id) {
        return new Identifier(MOD_ID, id);
    }

    public static Identifier id(String id, boolean bool) {
        if (bool) return new Identifier(MOD_ID, id);
        return new Identifier(id);
    }
}
