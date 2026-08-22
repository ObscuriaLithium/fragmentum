package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.content.world.tooltip.GroupTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.List;

public record ClientGroupTooltip(List<ClientTooltipComponent> components) implements ClientTooltipComponent {

    public static ClientGroupTooltip create(GroupTooltip tooltip) {
        return new ClientGroupTooltip(tooltip.components().stream().map(ClientTooltipComponent::create).toList());
    }

    public static <T extends ClientTooltipComponent> @Nullable T findFirst(List<ClientTooltipComponent> components, Class<T> type) {
        for (var component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
            if (component instanceof ClientGroupTooltip(List<ClientTooltipComponent> inner)) {
                final @Nullable var result = findFirst(inner, type);
                if (result != null) return result;
            }
        }
        return null;
    }

    @Override
    public int getHeight() {
        var totalHeight = 0;
        for (var component : components) {
            totalHeight += component.getHeight();
        }
        return totalHeight;
    }

    @Override
    public int getWidth(Font font) {
        var maxWidth = 0;
        for (var component : components) {
            maxWidth = Math.max(maxWidth, component.getWidth(font));
        }
        return maxWidth;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        var offset = 0;
        for (var component : components) {
            component.renderImage(font, x, y + offset, graphics);
            offset += component.getHeight();
        }
    }

    @Override
    public void renderText(Font font, int mouseX, int mouseY, Matrix4f matrix, MultiBufferSource.BufferSource source) {
        var offset = 0;
        for (var component : components) {
            component.renderText(font, mouseX, mouseY + offset, matrix, source);
            offset += component.getHeight();
        }
    }
}
