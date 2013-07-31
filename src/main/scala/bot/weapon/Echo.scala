package cc.hypo.golem.bot.weapon

import akka.actor.Actor
import cc.hypo.golem.bot.hipchat._

class Echo extends Actor {
  def receive = {
    case Asked(msg, room) if msg.bodyWithoutFirstMention.startsWith("echo ") => {
      room.send(msg.bodyWithoutFirstMention.substring(5))
    }
  }
}