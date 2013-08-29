package cc.hypo.utilities

import play.api.libs.json._
import play.api.libs.ws._
import scala.util._
import scala.sys.process._
import scala.concurrent._
import scala.concurrent.duration._

case class GistFile(val filename: String, val content: String)

case class Gist(val token: String) {
  def jsonStringForGist(description: String, public: Boolean, files: Set[GistFile]): String = {
    val filesJson = Json.toJson(files.map(gf ⇒ (gf.filename -> Json.obj("content" -> gf.content))).toMap)
    Json.prettyPrint(Json.obj("description" -> description, "public" -> public, "files" -> filesJson))
  }

  def createGist(description: String, isPublic: Boolean, files: Set[GistFile]): Try[String] = {
    val respF = WS.url("https://api.github.com/gists").withHeaders("Authorization" -> s"token $token").post(jsonStringForGist(description, isPublic, files))

    val resp = Await.result(respF, 30.seconds)
    val url = (Json.parse(resp.body) \ "html_url").as[String]
    Success(url)
  }
}