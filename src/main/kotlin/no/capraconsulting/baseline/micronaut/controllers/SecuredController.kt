package no.capraconsulting.baseline.micronaut.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import no.capraconsulting.baseline.micronaut.domain.Health
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/secured")
class SecuredController {
    private val log: Logger = LoggerFactory.getLogger(SecuredController::class.java)

    @Get("/health")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured("ROLE_ADMIN")
    fun securedHealth(): HttpResponse<Health> {
        log.info("Secured health called")
        return HttpResponse.ok(Health())
    }
}