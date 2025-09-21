package dev.obscuria.fragmentum.fabric

import dev.obscuria.fragmentum.Fragmentum
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.*
import dev.obscuria.fragmentum.server.FragmentumServerEvents as Server

fun commonInit() {

    Fragmentum.init()

    SERVER_STARTING.register { s -> Server.SERVER_STARTING broadcast { it.invoke(s) } }
    SERVER_STARTED.register { s -> Server.SERVER_STARTED broadcast { it.invoke(s) } }
    START_DATA_PACK_RELOAD.register { s, m -> Server.START_DATA_PACK_RELOAD broadcast { it.invoke(s, m) } }
    END_DATA_PACK_RELOAD.register { s, m, b -> Server.END_DATA_PACK_RELOAD broadcast { it.invoke(s, m, b) } }
    SYNC_DATA_PACK_CONTENTS.register { s, b -> Server.SYNC_DATA_PACK_CONTENTS broadcast { it.invoke(s, b) } }
    SERVER_STOPPING.register { s -> Server.SERVER_STOPPING broadcast { it.invoke(s) } }
    SERVER_STOPPED.register { s -> Server.SERVER_STOPPED broadcast { it.invoke(s) } }
}