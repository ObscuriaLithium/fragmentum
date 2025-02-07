package dev.obscuria.fragmentum.api

interface Platform
{
    fun getPlatformName(): String

    fun isModLoaded(modId: String): Boolean

    fun isClient(): Boolean

    fun isDedicatedServer(): Boolean = !isClient()

    fun isDevelopmentEnvironment(): Boolean
}