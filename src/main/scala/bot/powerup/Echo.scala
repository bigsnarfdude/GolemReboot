package cc.hypo.golem.bot.powerup

import akka.actor.Actor
import cc.hypo.golem.bot.hipchat._

class Echo extends Actor {
  def receive = {

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("echo ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(5))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("google ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(7))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("dns ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(7))
    }

  }
}