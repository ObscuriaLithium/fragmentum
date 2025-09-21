package dev.obscuria.fragmentum.fabric.service

import dev.obscuria.fragmentum.fabric.impl.FabricRegistrar
import dev.obscuria.fragmentum.registry.Registrar
import dev.obscuria.fragmentum.service.*

class FabricServices : FragmentumServices {

    override fun registrar(modId: String): Registrar = FabricRegistrar(modId)

    override fun factory(): FactoryService = FabricFactoryService

    override fun network(): NetworkService = FabricNetworkService

    override fun server(): ServerService = FabricServerService

    override fun client(): ClientService = FabricClientService
}