package dev.obscuria.fragmentum.platform.forge;

//? forge {
import dev.obscuria.fragmentum.FragmentumClient;

public final class ForgeClientEntrypoint {

	public static void init() {
		FragmentumClient.INSTANCE.onInitializeClient();
	}
}
//?}
