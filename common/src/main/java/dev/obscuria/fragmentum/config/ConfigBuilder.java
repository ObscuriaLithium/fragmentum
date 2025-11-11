package dev.obscuria.fragmentum.config;

import dev.obscuria.fragmentum.Fragmentum;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@ApiStatus.Internal
public final class ConfigBuilder {

    public static <T extends ConfigLayout> Instance build(T layout) {
        try {
            final var builder = new ForgeConfigSpec.Builder();
            final var rootLayer = buildLayer(builder, layout, null);
            return new Instance(builder.build(), rootLayer);
        } catch (Exception exception) {
            throw new IllegalArgumentException("Failed to build config layout: " + layout, exception);
        }
    }

    private static Layer buildLayer(ForgeConfigSpec.Builder builder, Object instance, @Nullable String sectionName) {
        final var values = new ArrayList<Value>();
        final var innerLayers = new ArrayList<Layer>();
        final var inner = sectionName != null && !sectionName.isEmpty();

        if (inner) builder.push(sectionName);

        for (var field : instance.getClass().getDeclaredFields()) {

            final var section = field.getAnnotation(ConfigOptions.Section.class);
            final @Nullable var category = section == null ? null : section.value();

            if (category != null) {
                try {
                    final var nestedInstance = field.get(instance);
                    if (nestedInstance == null) continue;
                    final var nestedLayer = buildLayer(builder, nestedInstance, category);
                    innerLayers.add(nestedLayer);
                } catch (Exception exception) {
                    Fragmentum.LOGGER.error("Failed to create nested config object: {}", field, exception);
                }
            } else {
                if (field.getAnnotation(ConfigOptions.Value.class) == null) continue;
                final var configValue = buildValue(builder, instance, field);
                values.add(new Value(field, configValue));
            }
        }

        if (inner) builder.pop();

        return new Layer(instance, values, innerLayers);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static ForgeConfigSpec.ConfigValue<?> buildValue(ForgeConfigSpec.Builder builder, Object instance, Field field) {
        final var type = field.getType();

        try {
            ConfigOptions.applyComment(field, builder::comment);
            if (type == int.class || type == Integer.class) {
                return builder.defineInRange(
                        ConfigOptions.getValueName(field),
                        field.getInt(instance),
                        (int) ConfigOptions.getRangeMin(field),
                        (int) ConfigOptions.getRangeMax(field));
            } else if (type == double.class || type == Double.class) {
                return builder.defineInRange(
                        ConfigOptions.getValueName(field),
                        field.getDouble(instance),
                        ConfigOptions.getRangeMin(field),
                        ConfigOptions.getRangeMax(field));
            } else if (type == boolean.class || type == Boolean.class) {
                return builder.define(
                        ConfigOptions.getValueName(field),
                        field.getBoolean(instance));
            } else if (type == String.class) {
                return builder.define(
                        ConfigOptions.getValueName(field),
                        (String) field.get(instance));
            } else if (type.isEnum()) {
                return builder.defineEnum(ConfigOptions.getValueName(field), (Enum) field.get(instance));
            } else if (type == List.class) {

                final var genericType = (java.lang.reflect.ParameterizedType) field.getGenericType();
                final var elementType = (Class<?>) genericType.getActualTypeArguments()[0];

                if (elementType == String.class) {
                    final var defaultList = (List<String>) field.get(instance);
                    return builder.defineList(ConfigOptions.getValueName(field), defaultList, it -> it instanceof String);
                } else if (elementType == Integer.class) {
                    final var defaultList = (List<Integer>) field.get(instance);
                    return builder.defineList(ConfigOptions.getValueName(field), defaultList, it -> it instanceof Integer);
                } else if (elementType == Double.class) {
                    final var defaultList = (List<Double>) field.get(instance);
                    return builder.defineList(ConfigOptions.getValueName(field), defaultList, it -> it instanceof Double || it instanceof Float);
                } else if (elementType == Boolean.class) {
                    final var defaultList = (List<Boolean>) field.get(instance);
                    return builder.defineList(ConfigOptions.getValueName(field), defaultList, it -> it instanceof Boolean);
                } else {
                    throw new IllegalArgumentException("Unsupported config list element type: " + elementType);
                }
            } else {
                throw new IllegalArgumentException("Unsupported config field type: " + type);
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException("Failed to build config value: " + field, exception);
        }
    }

    public record Layer(Object instance, List<Value> values, List<Layer> innerLayers) {

        public void updateAll() {
            values.forEach(it -> it.update(instance));
            innerLayers.forEach(Layer::updateAll);
        }
    }

    public record Value(Field field, ForgeConfigSpec.ConfigValue<?> value) {

        public void update(Object instance) {
            try {
                field.set(instance, value.get());
            } catch (Exception exception) {
                Fragmentum.LOGGER.error("Failed to set config value: {}", field, exception);
            }
        }
    }

    public record Instance(IConfigSpec<?> spec, Layer rootLayer) {

        public <T extends ConfigLayout> void maybeUpdate(ModConfig config, T layout, Consumer<T> listener) {
            if (config.getSpec() != spec) return;
            rootLayer.updateAll();
            listener.accept(layout);
        }
    }
}
