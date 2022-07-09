package ml.pkom.lunachat.mixin;

import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Consumer;

@Mixin(ServerPlayNetworkHandler.class)
public interface ServerPlayNetworkHandlerAccessor {

    @Invoker("filterText")
    public void invokeFilterText(String text, Consumer<FilteredMessage<String>> consumer);
}
