package dev.obscuria.fragmentum.server;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import org.apache.commons.lang3.NotImplementedException;

@SuppressWarnings("unused")
public interface FragmentumServerRegistry
{
    static void registerCommand(CommandRegistrar registrar)
    {
        throw new NotImplementedException();
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
