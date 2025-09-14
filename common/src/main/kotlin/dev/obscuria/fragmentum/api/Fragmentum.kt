package dev.obscuria.fragmentum.api

import dev.obscuria.fragmentum.api.service.FragmentumServices
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.ServiceLoader

object Fragmentum {

    const val MODID: String = "fragmentum"
    const val DISPLAY_NAME: String = "Fragmentum"
    @JvmStatic
    val LOGGER: Logger = LoggerFactory.getLogger(DISPLAY_NAME)
    @JvmStatic
    val PLATFORM: Platform = ServiceLoader.load(Platform::class.java).first()

    internal val SERVICES: FragmentumServices = ServiceLoader.load(FragmentumServices::class.java).first()
}