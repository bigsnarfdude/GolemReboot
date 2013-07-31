package cc.hypo.golem

import cc.hypo.golem.bot.Bot
import cc.hypo.golem.bot.hipchat.Hipchat
import cc.hypo.golem.bot.weapon.Echo
import com.typesafe.config._
import scala.collection.JavaConverters._

import akka.actor._

object Golem extends App {
  val conf = ConfigFactory.load()
  // if (!conf.hasPath("gtalk-username") || !conf.hasPath("gtalk-password")) {
  //   System.err.println("Need to specify gtalk-username and gtalk-password.")
  //   sys.exit
  // }

  // val gtalkUsername = conf.getString("gtalk-username")
  // val gtalkPassword = conf.getString("gtalk-password")

  // val connectionConfig = new ConnectionConfiguration("talk.google.com", 5222, "gmail.com")
  // connectionConfig.setCompressionEnabled(true)
  // connectionConfig.setSASLAuthenticationEnabled(true)

  // val connection = new XMPPConnection(connectionConfig)
  // connection.connect()
  // connection.login(gtalkUsername, gtalkPassword)

  // val chatManager = connection.getChatManager
  // chatManager.addChatListener(new ConversationListener())

  // while (true) {
  //   System.in.read
  //   Thread.sleep(100)
  // }
  // System.out.close
  // System.err.close
  
  val system = ActorSystem("Golem")

  val handler = system.actorOf(Props[Echo], name="echo")

  val hipchatUsername = conf.getString("hipchat-username")
  val hipchatPassword = conf.getString("hipchat-password")
  val hipchatNickname = conf.getString("hipchat-nickname")
  val hipchatAuthToken = conf.getString("hipchat-auth-token")
  val hipchatRooms = conf.getObjectList("hipchat-rooms").asScala

  val bot = new Bot(hipchatUsername, hipchatPassword, hipchatNickname, Hipchat(hipchatAuthToken))
  hipchatRooms.foreach((roomConfig: ConfigObject) =>
    bot.joinRoom(roomConfig.get("room").unwrapped.asInstanceOf[String],
                 system.actorSelection(roomConfig.get("handler").unwrapped.asInstanceOf[String]))
  )

}

