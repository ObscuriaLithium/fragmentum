package dev.obscuria.fragmentum.service

import dev.obscuria.fragmentum.registry.Registrar

interface FragmentumServices {

    fun registrar(modId: String): Registrar

    fun factory(): FactoryService

    fun network(): NetworkService

    fun server(): ServerService

    fun client(): ClientService
}