package dev.oaiqiy.mirai.truenas.scale.plugin

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    val URL_PREFIX : String by value("")
    val TOKEN : String by value("")
    val ADMIN_NUMBER : Long by value(0L)
}