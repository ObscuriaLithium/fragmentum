package dev.obscuria.fragmentum.platform.fabric;

//? fabric {
/*import dev.obscuria.fragmentum.Fragmentum;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.obscuria.fragmentum.FragmentumContextImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		Fragmentum.INSTANCE.onInitialize();
		ServerLifecycleEvents.SERVER_STARTING.register(FragmentumContextImpl.INSTANCE::notifyServerStarting);
		ServerLifecycleEvents.SERVER_STOPPED.register(FragmentumContextImpl.INSTANCE::notifyServerStopped);
	}
}
*///?}
