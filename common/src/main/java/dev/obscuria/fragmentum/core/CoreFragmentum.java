package dev.obscuria.fragmentum.core;

import dev.obscuria.fragmentum.api.v1.common.FragmentumEvents;
import dev.obscuria.fragmentum.api.v1.common.text.AutoDescription;
import dev.obscuria.fragmentum.api.v1.common.text.TextWrapper;
import dev.obscuria.fragmentum.core.v1.common.AllOfTooltipComponent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.ApiStatus;

import java.util.Optional;
import java.util.function.Consumer;

@ApiStatus.Internal
public final class CoreFragmentum
{
    public static void init() {}

    public static void modifyTooltip(ItemStack stack, Consumer<Component> consumer)
    {
        if (!stack.getItem().getClass().isAnnotationPresent(AutoDescription.class)) return;
        final var annotation = stack.getItem().getClass().getAnnotation(AutoDescription.class);
        final var id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        final var description = Component.translatable(id.toLanguageKey("item", "desc"));
        final var lines = TextWrapper.create(description)
                .withStyle(Style.EMPTY.withColor(annotation.style()))
                .withMaxLength(40)
                .build();
        lines.forEach(consumer);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static Optional<TooltipComponent> gatherTooltipImages(ItemStack stack,
                                                                 Optional<TooltipComponent> defaultImage)
    {
        final var customImages = Lists.<TooltipComponent>newArrayList();
        FragmentumEvents.GATHER_TOOLTIP_IMAGES.broadcast(listener -> listener.invoke(stack, customImages::add));

        if (customImages.isEmpty()) return defaultImage;
        defaultImage.ifPresent(customImages::addFirst);
        return Optional.of(new AllOfTooltipComponent(customImages));
    }
}
