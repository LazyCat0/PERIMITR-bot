package lazy

import net.dv8tion.jda.api.requests.*
import dev.minn.jda.ktx.jdabuilder.*
import dev.minn.jda.ktx.events.*
import dev.minn.jda.ktx.messages.*
import net.dv8tion.jda.api.events.message.*
import net.dv8tion.jda.api.hooks.*
import dev.minn.jda.ktx.util.*
import dev.minn.jda.ktx.coroutines.*


// основной код
// Логи комманд
object Listener : ListenerAdapter() {
    private val log by SLF4J

    override fun onMessageReceived(event: MessageReceivedEvent) {
        log.info("[{}] {}: {}", event.channel.name, event.author.asTag, event.message.contentDisplay)
    }
}
//сам бот
fun main() {
    val bot = light("токен нахуй", enableCoroutines=true) {
        intents += listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
    }



    bot.listener<MessageReceivedEvent> {
        val channel = it.channel
        val message = it.message
        val content = message.contentRaw
        if (content.startsWith("p.emojis")) {
            // Send typing indicator and wait for it to arrive
            channel.sendTyping().await()
            channel.send("<:warning:1393326786829619320>").queue()
            channel.send("<:error:1393326821520703488>").queue()
            channel.send("<:whiteplate:1393326772212334704>").queue()
            channel.send("<:cross:1393326831859404980>").queue()
            channel.send("<:checkmark:1393326843041419474>").queue()
            channel.send("<:fire:1393326810044956705>").queue()
            channel.send("<:lazycaticon:1393326799106211881>").queue()
        } //p.emojis
        if (content.startsWith("p.info")) {
            channel.sendTyping().await()
            channel.sendMessageEmbeds( Embed { // Builds a MessageEmbed
                    title = "Информация о боте"
                    field {
                        name = "Тупо бот созданный для проекта PERIMITR"
                        inline = false
                    }
                color = 0x7b00ff
                    title = "Информация о создателе"
                field {
                    name = "LazyCat <:lazycaticon:1393325062093934654>"
                    inline = false

                }
                color = 0x7b00ff
            }).queue()

        } //p.info
        if (content.startsWith("p.workers")) {
            channel.sendTyping().await()
            channel.sendMessageEmbeds( Embed { // Builds a MessageEmbed
                title = "Информация о работниках"
                field {
                    name = "В РАЗРАБОТКЕ"
                    inline = false
                } //p.emojis
                color = 0x7b00ff
            }).queue()

        } //p.workers
        if (content.startsWith("p.help")) {
            // Send typing indicator and wait for it to arrive
            channel.sendTyping().await()
            channel.send("Вот список команд в боте созданном для PERIMITR").queue()
            channel.sendMessageEmbeds( Embed { // Builds a MessageEmbed
                title = "Список комманд в боте"
                description = "*ВНИМАНИЕ*: При использовании комманд, не забудьте поставить префикс *.p* ***А ТАКЖЕ НЕ ЗАБЫВАЙТЕ ЧТО КОМАНДЫ*** */* ***В РАЗАРБОТКЕ***"
                field {
                    name = "p.emojis"
                    value = "Показывает все эмодзи бота"
                    inline = false
                } //p.emojis
                field {
                    name = "p.help"
                    value = "Этот список"
                    inline = false
                } // p.help
                field {
                    name = "p.info"
                    value = "Информация о боте и проекте для которого он создан"
                    inline = false
                } // p.info
                field {
                    name = "p.workers"
                    value = "Список работников проекта и их должности"
                    inline = false
                } //p.workers
                color = 0x8b00ff
            }).queue()
        } //p.help
    }
}
