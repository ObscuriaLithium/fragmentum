package dev.obscuria.fragmentum.api.v1.server;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@SuppressWarnings("unused")
public interface ObscureServerRegistry
{
    static void registerCommand(CommandRegistrar registrar)
    {
        V1Server.INSTANCE.registerCommand(registrar);
    }

    @FunctionalInterface
    interface CommandRegistrar
    {
        void register(CommandDispatcher<CommandSourceStack> dispatcher,
                      CommandBuildContext registryAccess,
                      Commands.CommandSelection environment);
    }
}
