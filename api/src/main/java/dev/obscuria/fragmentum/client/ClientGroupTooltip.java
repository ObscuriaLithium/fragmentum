package dev.obscuria.fragmentum.client;

import dev.obscuria.fragmentum.content.world.tooltip.GroupTooltip;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

import java.util.List;

public record ClientGroupTooltip(List<ClientTooltipComponent> components) implements ClientTooltipComponent {

    public static ClientGroupTooltip create(GroupTooltip tooltip) {
        throw new NotImplementedException();
    }

    public static <T extends ClientTooltipComponent> @Nullable T findFirst(List<ClientTooltipComponent> components, Class<T> type) {
        throw new NotImplementedException();
    }

    @Override
    public int getHeight() {
        throw new NotImplementedException();
    }

    @Override
    public int getWidth(Font font) {
        throw new NotImplementedException();
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics graphics) {
        throw new NotImplementedException();
    }

    @Override
    public void renderText(Font font, int mouseX, int mouseY, Matrix4f matrix, MultiBufferSource.BufferSource source) {
        throw new NotImplementedException();
    }
}
