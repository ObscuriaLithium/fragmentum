package dev.obscuria.fragmentum.mixin;

//? forge && 1.20.1 {
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BuiltInRegistries.class)
@MixinEnvironment(type = MixinEnvironment.Env.DEFAULT)
public interface BuiltInRegistriesAccessor {

    @Accessor("WRITABLE_REGISTRY")
    static WritableRegistry<WritableRegistry<?>> fragmentum$getWritableRegistry() {
        throw new AssertionError();
    }
}
//?}
