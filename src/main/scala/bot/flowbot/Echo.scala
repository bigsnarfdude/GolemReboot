package cc.hypo.golem.bot.powerup

import akka.actor.Actor
import cc.hypo.golem.bot.hipchat._

class Echo extends Actor {
  def receive = {

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("10-11") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("10-28") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("10-33") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("10-99") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("checklist hacked") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("command scp get") => {
      speaker ! (msg.scpExampleGetFile)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("command scp put") => {
      speaker ! (msg.scpExamplePutFile)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("command ssh") => {
      speaker ! (msg.sshExample)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("dns ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(4))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("echo ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(5))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("emo ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(4))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("gif ") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("gist ") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("google ") => {
      speaker ! (msg.bodyWithoutFirstMention.substring(7))
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }
    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("help") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("checklist hacked") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }

    case Asked(msg, speaker) if msg.bodyWithoutFirstMention.startsWith("image") => {
      speaker ! (msg.help)
    }
  }
}