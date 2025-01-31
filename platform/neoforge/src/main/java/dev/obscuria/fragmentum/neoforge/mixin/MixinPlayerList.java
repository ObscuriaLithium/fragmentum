package dev.obscuria.fragmentum.neoforge.mixin;

import dev.obscuria.fragmentum.api.v1.server.FragmentumServerEvents;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList
{
    @Inject(method = "placeNewPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundUpdateRecipesPacket;<init>(Ljava/util/Collection;)V"))
    private void placeNewPlayer_listener(
            Connection connection,
            ServerPlayer player,
            CommonListenerCookie cookie,
            CallbackInfo info)
    {
        FragmentumServerEvents.SYNC_DATA_PACK_CONTENTS.broadcast(listener -> listener.invoke(player, true));
    }

    @Inject(method = "reloadResources", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/common/ClientboundUpdateTagsPacket;<init>(Ljava/util/Map;)V"))
    private void reloadResources_listener(CallbackInfo info)
    {
        for (var player : ((PlayerList) (Object) this).getPlayers())
        {
            FragmentumServerEvents.SYNC_DATA_PACK_CONTENTS.broadcast(listener -> listener.invoke(player, false));
        }
    }
}
