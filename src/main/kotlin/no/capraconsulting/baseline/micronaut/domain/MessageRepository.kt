package no.capraconsulting.baseline.micronaut.domain

import no.capraconsulting.baseline.micronaut.domain.dao.MessageDao
import no.capraconsulting.baseline.micronaut.domain.model.Message
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
open class MessageRepository(@Inject val dataSource: DataSource) {

    fun saveMesssage(message: Message): Message {
        dataSource.connection.use { connection ->
            MessageDao.from(message).saveMessage(connection).use {
                try {
                    val i = it.executeUpdate()
                    if (i == 0) {
                        log.warn("Error inserting into database, no rows affected")
                    }
                    log.debug("Inserting into database")
                } catch (ex: Exception) {
                    log.error("Error inserting into database", ex)
                }
            }
        }

        return message
    }

    fun getAllMesages(): List<Message>? {
        val messages = mutableListOf<MessageDao>()

        dataSource.connection.use {
            MessageDao.selectAll(connection = it).use { stmt ->
                val resultSet = stmt.executeQuery()
                while (resultSet.next()) messages.add(MessageDao.from(resultSet))
            }
        }

        return messages.map { it.toMessage() }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MessageRepository::class.java)
    }
}