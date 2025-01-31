package dev.obscuria.fragmentum.neoforge.mixin;

import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
    @Shadow private MinecraftServer.ReloadableResources resources;

    @Inject(method = "reloadResources", at = @At("HEAD"))
    private void reloadResources_headListener(
            Collection<String> selected,
            CallbackInfoReturnable<CompletableFuture<Void>> cir)
    {
        final var server = (MinecraftServer) (Object) this;
        final var manager = this.resources.resourceManager();
        FragmentumServerEvents.START_DATA_PACK_RELOAD.broadcast(handler -> handler.invoke(server, manager));
    }

    @Inject(method = "reloadResources", at = @At("TAIL"))
    private void reloadResources_tailListener(
            Collection<String> selected,
            CallbackInfoReturnable<CompletableFuture<Void>> cir)
    {
        final var server = (MinecraftServer) (Object) this;
        final var manager = this.resources.resourceManager();
        cir.getReturnValue().handleAsync((value, throwable) ->
        {
            FragmentumServerEvents.END_DATA_PACK_RELOAD.broadcast(handler -> handler.invoke(server, manager, throwable == null));
            return value;
        }, server);
    }

    @Inject(method = "saveEverything", at = @At("HEAD"))
    private void saveEverything_headListener(
            boolean suppressLog,
            boolean flush,
            boolean forced,
            CallbackInfoReturnable<Boolean> info)
    {
        final var server = (MinecraftServer) (Object) this;
        FragmentumServerEvents.BEFORE_SAVE.broadcast(handler -> handler.invoke(server, flush, forced));
    }

    @Inject(method = "saveEverything", at = @At("TAIL"))
    private void saveEverything_tailListener(
            boolean suppressLog,
            boolean flush,
            boolean forced,
            CallbackInfoReturnable<Boolean> info)
    {
        final var server = (MinecraftServer) (Object) this;
        FragmentumServerEvents.AFTER_SAVE.broadcast(handler -> handler.invoke(server, flush, forced));
    }
}