package dev.obscuria.fragmentum.tools.text

import net.minecraft.ChatFormatting

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class AutoDescription(val style: ChatFormatting = ChatFormatting.GRAY)