package ml.pkom.lunachat.mixin;

import net.minecraft.server.filter.TextStream;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@Mixin(ServerPlayNetworkHandler.class)
public interface ServerPlayNetworkHandlerAccessor {

    @Invoker("filterText")
    public <T, R> void invokeFilterText(T text, Consumer<R> consumer, BiFunction<TextStream, T, CompletableFuture<R>> backingFilterer);
}
