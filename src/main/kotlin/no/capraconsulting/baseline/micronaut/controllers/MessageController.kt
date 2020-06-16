package no.capraconsulting.baseline.micronaut.controllers

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.reactivex.Single
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import no.capraconsulting.baseline.micronaut.domain.MessageRepository
import no.capraconsulting.baseline.micronaut.domain.model.Message
import javax.inject.Inject

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/messages")
class MessageController(@Inject val messageRepository: MessageRepository) {

    @Get(produces = [MediaType.APPLICATION_JSON])
    @Operation(
            summary = "Get a list of messages",
            description = "Will retrieve a list of messages",
            responses = [ApiResponse(
                    responseCode = "200",
                    content = arrayOf(
                            Content(mediaType = "application/json",
                                    schema = Schema(implementation = Message::class, type = "array"))
                    ),
                    description = "A successful response containing activities."
            ), ApiResponse(
                    responseCode = "400",
                    description = "When one of the input parameters is not valid."
            )]
    )
    fun getMessages(): HttpResponse<List<Message>> {
        val messages = messageRepository.getAllMesages()

        if (messages.isNullOrEmpty()) {
            return HttpResponse.notFound()
        }

        return HttpResponse.ok(messages)
    }

    @Post(consumes = [MediaType.TEXT_PLAIN])
    fun createMessage(
            @Body value: String
    ): HttpResponse<Message> {
        return HttpResponse.ok(messageRepository.saveMesssage(Message(value)))
    }

}