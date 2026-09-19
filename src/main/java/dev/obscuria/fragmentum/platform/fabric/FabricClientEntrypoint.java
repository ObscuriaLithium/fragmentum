package dev.obscuria.fragmentum.platform.fabric;

//? fabric {
/*import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.obscuria.fragmentum.FragmentumClient;
import dev.obscuria.fragmentum.client.TooltipComponentRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		FragmentumClient.INSTANCE.onInitializeClient();
		TooltipComponentCallback.EVENT.register(TooltipComponentRegistry.INSTANCE::create);
	}
}
*///?}
