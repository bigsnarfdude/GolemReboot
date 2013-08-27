package cc.hypo.golem.bot.hipchat

import org.jivesoftware.smackx.muc.MultiUserChat

import dispatch._, Defaults._
import spray.json._
import spray.json.DefaultJsonProtocol._
import akka.actor.ActorRef

case class Room(jid: String, muc: MultiUserChat) {
  def send(msg: String) = {
    muc.sendMessage(msg)
  }
}

case class HipchatUser(uid: String, name: String, email: String, mentionName: String)


case class Hipchat(authToken: String) {

  def users: Seq[HipchatUser] = {
    val hipchatList = Http(url(s"https://api.hipchat.com/v1/users/list?format=json&auth_token=$authToken") OK as.String)
    val respJson = hipchatList().asJson.convertTo[Map[String, List[JsObject]]]
    val userJsonList: Seq[JsObject] = respJson("users")

    userJsonList.map((u: JsObject) =>
      u.getFields("user_id", "name", "email", "mention_name") match {
      case Seq(JsNumber(uid), JsString(name), JsString(email), JsString(mentionedName)) => HipchatUser(uid.toString, name, email, mentionedName)
    })
  }
}

case class Message(from: String, body: String) {
  def mentions: List[String] = body.split("[^@\\w]").toList.filter(_.startsWith("@")).map(_.substring(1))

  def bodyWithoutMention: String = body.replaceAll("@\\w+", "")
  def bodyWithoutFirstMention: String = body.replaceFirst("@\\w+", "").trim
}

case class Heard(message: Message, speaker: ActorRef)
case class Asked(message: Message, speaker: ActorRef)