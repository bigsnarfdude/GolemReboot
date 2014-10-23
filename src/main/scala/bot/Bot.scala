package cc.hypo.golem.bot

import org.jivesoftware.smack._
import org.jivesoftware.smack.packet._
import org.jivesoftware.smackx.muc.MultiUserChat
import akka.actor.{ActorSystem, Props, Actor, ActorSelection}

import hipchat._

class SpeakingActor(val room: Room) extends Actor {
  def receive = {
    case (sentence: String) => room.send(sentence)
  }
}

class Bot(username: String, password: String, nickname: String, hipchat: Hipchat, system: ActorSystem) {
  val XMPP_URL = "chat.hipchat.com"
  val CONN_PORT = 5222 
  val CONF_URL = "conf.hipchat.com" 

  val connection: XMPPConnection = new XMPPConnection(new ConnectionConfiguration(XMPP_URL, CONN_PORT))
  connection.connect()
  
  connection.login(username, password, "bot")

  val user: HipchatUser = hipchat.users.find(u => u.name == nickname).get

  def joinRoom(roomName: String, handlers: ActorSelection) = {
    println("trying to join room")
    val jid = if (roomName.contains("@")) roomName else s"$roomName@$CONF_URL"
    val muc = new MultiUserChat(connection, jid)
    val room = Room(jid, muc)

    val speaker = system.actorOf(Props(classOf[SpeakingActor], room), jid)

    muc.join(nickname, password)

    muc.addMessageListener(new PacketListener {
      def processPacket(p: Packet) {
        p match {
          case m: org.jivesoftware.smack.packet.Message => {
            val fullname = m.getFrom.split("/").drop(1).mkString("/")
            if (fullname == user.name) {
              // ignore bot's message
            } else {
              val fromName = hipchat.users.find(u => u.name == fullname).map(u => "@" + u.mentionName).getOrElse(fullname)
              val msg = cc.hypo.golem.bot.hipchat.Message(from = fromName, body = m.getBody)

              handlers ! (msg.mentions match {
                case user.mentionName :: xs if msg.body.trim.startsWith("@") => Asked(message = msg, speaker = speaker)
                case other => Heard(message = msg, speaker = speaker)
              })
            }
          }
          case packet => {}
        }
      }
    })
  }

}
