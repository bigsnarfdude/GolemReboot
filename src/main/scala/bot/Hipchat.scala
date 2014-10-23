package cc.hypo.golem.bot.hipchat

import org.jivesoftware.smackx.muc.MultiUserChat
import play.api.libs.json._
import play.api.libs.ws.{Response, WS}
import scala.concurrent._
import scala.concurrent.duration._

import akka.actor.ActorRef

case class Room(jid: String, muc: MultiUserChat) {
  def send(msg: String) = {
    muc.sendMessage(msg)
  }
}

case class HipchatUser(uid: String, name: String, mentionName: String)


case class Hipchat(authToken: String) {
  var cachedUsers: Seq[HipchatUser] = null

  def users: Seq[HipchatUser] = {
    if (cachedUsers == null) {
      
      val target = s"https://api.hipchat.com/v2/user?format=json&auth_token=$authToken"
      val hipchatListF: Future[play.api.libs.ws.Response] = WS.url(target).get()
      val resp: Response = Await.result(hipchatListF, 30.seconds)
      val respJson = Json.parse(resp.body)

      cachedUsers = for (u <- (respJson \ "items").as[Seq[Map[String, JsValue]]])
        yield HipchatUser(u("id").as[Int].toString, u("name").as[String], u("mention_name").as[String])
    }
    cachedUsers
  }
}

case class Message(from: String, body: String) {
  def mentions: List[String] = body.split("[^@\\w]").toList.filter(_.startsWith("@")).map(_.substring(1))

  def bodyWithoutMention: String = body.replaceAll("@\\w+", "")
  def bodyWithoutFirstMention: String = body.replaceFirst("@\\w+", "").trim
}

case class Heard(message: Message, speaker: ActorRef)
case class Asked(message: Message, speaker: ActorRef)
