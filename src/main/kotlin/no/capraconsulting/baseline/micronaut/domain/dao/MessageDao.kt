package no.capraconsulting.baseline.micronaut.domain.dao

import no.capraconsulting.baseline.micronaut.domain.model.Message
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class MessageDao(
        val id: Long,
        val message: String
) {
    fun toMessage() = Message(message)

    fun saveMessage(connection: Connection): PreparedStatement {
        val stmt = connection.prepareStatement("""
            insert into message(message) values (?)
        """.trimIndent())
        stmt.setString(1, message)
        return stmt
    }

    companion object {
        fun from(message: Message) = MessageDao(
                id = 0,
                message = message.message
        )

        fun selectAll(connection: Connection): PreparedStatement = connection.prepareStatement("""
           select * from message 
        """.trimIndent())

        fun from(resultSet: ResultSet) = MessageDao(
                id = resultSet.getLong("id"),
                message = resultSet.getString("message")
        )
    }
}
