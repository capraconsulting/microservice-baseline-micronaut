package no.capraconsulting.baseline.micronaut.domain

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

class Health {
    companion object InitHealth {
        val startupTime :ZonedDateTime = ZonedDateTime.now()
        val version : String = Health::class.java.`package`.implementationVersion ?: "development"
        val service : String = Health::class.java.`package`.implementationTitle ?: "micronaut-baseline"
    }

    val runningSince : ZonedDateTime = InitHealth::startupTime.get()
    val version = InitHealth::version.get()
    val service = InitHealth::service.get()
    val now : ZonedDateTime = ZonedDateTime.now()
}