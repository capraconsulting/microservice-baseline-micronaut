package no.capraconsulting.baseline.micronaut.domain.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Message (
        @JsonProperty("message") val message: String
)
