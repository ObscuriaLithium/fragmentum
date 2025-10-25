package dev.obscuria.fragmentum.mixin;

import dev.obscuria.fragmentum.FragmentumProxy;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {

    @Inject(method = "runServer", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/MinecraftServer;buildServerStatus()Lnet/minecraft/network/protocol/status/ServerStatus;"))
    private void onServerStart(CallbackInfo info) {
        final var self = (MinecraftServer) (Object) this;
        FragmentumProxy.onServerStart(self);
    }

    @Inject(method = "stopServer", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/MinecraftServer;saveAllChunks(ZZZ)Z"))
    private void onServerStop(CallbackInfo info) {
        final var self = (MinecraftServer) (Object) this;
        FragmentumProxy.onServerStop(self);
    }
}