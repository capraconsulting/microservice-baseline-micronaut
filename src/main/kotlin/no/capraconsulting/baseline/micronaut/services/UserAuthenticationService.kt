package no.capraconsulting.baseline.micronaut.services

import io.micronaut.security.authentication.*
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class UserAuthenticationService  : AuthenticationProvider {
    private val log: Logger = LoggerFactory.getLogger(UserAuthenticationService::class.java)

    override fun authenticate(authenticationRequest: AuthenticationRequest<*, *>?): Publisher<AuthenticationResponse> {
        log.info("authentication request from user: ${authenticationRequest?.identity}")

        if(authenticationRequest?.identity == "user" && authenticationRequest.secret == "password") {
            return Flowable.just(UserDetails("user", listOf("ROLE_ADMIN")))
        }
        return Flowable.just(AuthenticationFailed())
    }

}