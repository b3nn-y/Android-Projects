package com.bennysamuel.testserverapp


import io.ktor.*
import io.ktor.server.application.Application


fun Application.module() {
    install(ContentNegotiation) {
        gson()
    }

    routing {
        get("/api/data") {
            call.respond(mapOf("message" to "Hello, world!"))
        }
    }
}