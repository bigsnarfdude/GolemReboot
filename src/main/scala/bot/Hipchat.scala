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
    println(cachedUsers)
    cachedUsers
  }
}

case class Message(from: String, body: String) {
  def mentions: List[String] = body.split("[^@\\w]").toList.filter(_.startsWith("@")).map(_.substring(1))

  def bodyWithoutMention: String = body.replaceAll("@\\w+", "")
  def bodyWithoutFirstMention: String = body.replaceFirst("@\\w+", "").trim
  def sshExample: String = "ssh -i privateKey.pem ubuntu@10.11.12.13"
  def scpExampleGetFile: String = "scp -i privateKey.pem ubuntu@10.11.12.13:/home/ubuntu/getThisFile /path/toFile/localhost"
  def scpExamplePutFile: String = "scp -i privateKey.pem /path/toPutFile/localhost ubuntu@10.11.12.13:/home/ubuntu/getThisFile"

  def help: String =
    """
      |incident response checklists
      |memory capture
      |routine live memory analysis reporting
      |elasticsearch netflows
      |asset listing
      |users listing
      |users login histories
      |remote login histories
      |password changes histories
      |patch management
      |last contact firewall
      |last contact SIEM
      |software inventories
      |graph analysis netflows
      |ports listing
      |connection listing
      |stats. history
      |anomaly detection
      |dns resolution
      |asn
      |malware
      |blacklists
      |whitelists
      |google
      |long running history
      |zip functionality and hosting
      |ssdeep
      |cuckoo sandbox submission
      |md5
      |google safebrowsing
      |Carbon Black
      |NetWitness Pivot Query
      |RSA NetWitness
      |RSA Security Analytics graphs
      |url void
      |safe search
      |malware domain search
      |centralops
      |bit9 md5
      |virustotal
      |dns
      |asn
      |netflow
      |internal search history
      |betweeness and centrality measures
      |mathy anomaly detection
      |ML AD
      |command lookup
      |gifs
      |emoticons
      |weather
      |jokes
      |compliments
      |new
      |horoscope
      |help
      |movie quotes
      |limericks
      |daily standup reminder
      |plugins like hubbot
    """.stripMargin

}

case class Heard(message: Message, speaker: ActorRef)
case class Asked(message: Message, speaker: ActorRef)
