package dev.obscuria.fragmentum.api.v1.server;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/**
 * Provides methods for registering server-specific functionalities, such as commands,
 * within the Fragmentum API.
 */
@SuppressWarnings("unused")
public interface FragmentumServerRegistry
{
    /**
     * Registers a command using the provided {@link CommandRegistrar}.
     * <p>
     * This method delegates the registration process to the underlying implementation
     * and allows developers to define server-specific commands.
     *
     * @param registrar An implementation of {@link CommandRegistrar} that specifies how
     *                  the command should be registered within the server's command dispatcher.
     */
    static void registerCommand(CommandRegistrar registrar)
    {
        V1Server.INSTANCE.registerCommand(registrar);
    }

    /**
     * A functional interface for defining how a command is registered within the server.
     */
    @FunctionalInterface
    interface CommandRegistrar
    {
        /**
         * Registers a command with the provided {@link CommandDispatcher}.
         *
         * @param dispatcher     The {@link CommandDispatcher} responsible for handling commands.
         * @param registryAccess The {@link CommandBuildContext} used for accessing registries.
         * @param environment    The {@link Commands.CommandSelection} that defines the environment
         *                       in which the command is available (e.g., single-player or multiplayer).
         */
        void register(CommandDispatcher<CommandSourceStack> dispatcher,
                      CommandBuildContext registryAccess,
                      Commands.CommandSelection environment);
    }
}