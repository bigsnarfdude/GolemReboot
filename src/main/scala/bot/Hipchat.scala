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

case class HipchatUser(uid: String, name: String, email: String, mentionName: String)


case class Hipchat(authToken: String) {

  def users: Seq[HipchatUser] = {
    val hipchatListF: Future[play.api.libs.ws.Response] = WS.url(s"https://api.hipchat.com/v1/users/list?format=json&auth_token=$authToken").get()
    val resp: Response = Await.result(hipchatListF, 30.seconds)
    val respJson = Json.parse(resp.body)

    for (u <- (respJson \ "users").as[Seq[Map[String, JsValue]]])
    yield HipchatUser(u("user_id").as[Long].toString, u("name").as[String], u("email").as[String], u("mention_name").as[String])
  }
}

case class Message(from: String, body: String) {
  def mentions: List[String] = body.split("[^@\\w]").toList.filter(_.startsWith("@")).map(_.substring(1))

  def bodyWithoutMention: String = body.replaceAll("@\\w+", "")
  def bodyWithoutFirstMention: String = body.replaceFirst("@\\w+", "").trim
}

case class Heard(message: Message, speaker: ActorRef)
case class Asked(message: Message, speaker: ActorRef)