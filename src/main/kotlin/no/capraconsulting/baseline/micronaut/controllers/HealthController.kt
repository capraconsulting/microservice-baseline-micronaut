package no.capraconsulting.baseline.micronaut.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import no.capraconsulting.baseline.micronaut.domain.Health

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/health")
class HealthController {

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun health(): HttpResponse<Health> {
        return HttpResponse.ok(Health())
    }
}