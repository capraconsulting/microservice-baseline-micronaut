package no.capraconsulting.baseline.micronaut

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
        info = Info(
                title = "Micronaut Microservice Baseline",
                version = "0.1",
                description = "Baseline for microservices implemented with Micronaut",
                contact = Contact(url = "https://.no", name = "Daniel Engfeldt", email = "den@capraconsulting.no")
        )
)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("no.capraconsulting.baseline.micronaut")
                .mainClass(Application.javaClass)
                .start()
    }
}