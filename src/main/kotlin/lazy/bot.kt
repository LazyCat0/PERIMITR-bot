package lazy

import net.dv8tion.jda.api.requests.*
import dev.minn.jda.ktx.jdabuilder.*
import dev.minn.jda.ktx.events.*
import dev.minn.jda.ktx.messages.*
import net.dv8tion.jda.api.events.message.*
import net.dv8tion.jda.api.hooks.*
import dev.minn.jda.ktx.util.*
import dev.minn.jda.ktx.coroutines.*
import dev.minn.jda.ktx.interactions.components.getOption
import net.dv8tion.jda.api.entities.User
import org.json.JSONObject
import java.io.File
import kotlin.time.Duration.Companion.seconds

// основной код
// Логи комманд
object Listener : ListenerAdapter() {
    private val log by SLF4J

    override fun onMessageReceived(event: MessageReceivedEvent) {
        log.info("[{}] {}: {}", event.channel.name, event.author.asTag, event.message.contentDisplay)
    }
}
// получение токена с json файла
fun getBotToken(): String? {
    return try {
        val file = File("src/main/resources/token.json").readText()
        val json = JSONObject(file)
        json.getString("bot_token")
    } catch (e: Exception) {
        println("${e.message}")
        null
    }
}
//сам бот
fun main() {
    val botToken = getBotToken()

    if (botToken == null) {
        println("Токен не загружен. Смотрите ошибку выше")
        return
    }
    val bot = light(botToken, enableCoroutines=true) {
        intents += listOf(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
    }



    bot.listener<MessageReceivedEvent> {
        val guild = it.guild
        val channel = it.channel
        val message = it.message
        val content = message.contentRaw
        val author = it.author
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
        }
        if (content.startsWith("p.info")) {
            channel.sendTyping().await()
            channel.sendMessageEmbeds( Embed { // Builds a MessageEmbed
                    title = "Информация о боте"
                    field {
                        name = "Тупо бот созданный для проекта PERIMITR"
                        description = "написан кстати на котлин"
                        inline = false
                    }
                color = 0x7b00ff
                    title = "Информация о создателе"
                field {
                    name = "LazyCat <:lazycaticon:1393325062093934654>"
                    description = "я хз что сюда написать"
                    inline = false

                }
                color = 0x7b00ff
            }).queue()

        }
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

        }
        if (content.startsWith("p.help")) {
            // Send typing indicator and wait for it to arrive
            channel.sendTyping().await()
            channel.send("Вот список команд в боте созданном для PERIMITR").queue()
            channel.sendMessageEmbeds( Embed { // Builds a MessageEmbed
                title = "Список комманд в боте"
                description = "*ВНИМАНИЕ*: При использовании комманд, не забудьте поставить префикс *.p* ***А ТАКЖЕ НЕ ЗАБЫВАЙТЕ ЧТО КОМАНДЫ*** */* ***В РАЗАРБОТКЕ***. Требуется подметить что присуствуют команды что требуют права модератора, они помечены при помощи ***M***. Команды что сейчас в разработке помечены при помощи ***D***"
                field {
                    name = "p.emojis **M**"
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
                    name = "p.workers **D**"
                    value = "Список работников проекта и их должности"
                    inline = false
                } // p.workers
                field {
                    name = "p.pingX @(пользователь) **M**"
                    value = "Отмечает указаного пользователя 10 раз"
                    inline = false
                } // p.pingX
                color = 0x8b00ff
            }).queue()
        }
        if (content.startsWith("p.pingX")) {
            // Send typing indicator and wait for it to arrive
            channel.sendTyping().await()
            val user = message.mentions.users.firstOrNull() ?: run {
                val matches = guild.retrieveMembersByPrefix(content.substringAfter("p.pingX "), 1).await()
                // Take first result, or null
                matches.firstOrNull()
            }
            if (user == null)
                channel.send("${author.asMention}, user which you try to ping is not found").queue()
            else
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
                channel.send("${user?.asMention}, you now pinged!").queue()
        }

    }
    bot.onCommand("test", 5.seconds) { event ->
        val user = event.getOption<User>("user")!!
        event.reply_(
            "This is testcommand, ${user.asMention}",
            ephemeral = true
        ).queue()

    }
}
