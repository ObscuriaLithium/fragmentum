package dev.obscuria.fragmentum.server;

import com.mojang.brigadier.CommandDispatcher;
import dev.obscuria.fragmentum.Fragmentum;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@SuppressWarnings("unused")
public interface FragmentumServerRegistry
{
    static void registerCommand(CommandRegistrar registrar)
    {
        Fragmentum.SERVICES.server().registerCommand(registrar);
    }

    @FunctionalInterface
    interface CommandRegistrar
    {
        void register(
                CommandDispatcher<CommandSourceStack> dispatcher,
                CommandBuildContext registryAccess,
                Commands.CommandSelection environment);
    }
}
