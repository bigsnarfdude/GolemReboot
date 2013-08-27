package cc.hypo.utilities

import com.typesafe.config._
import scala.collection.JavaConverters._
import scala.language.implicitConversions

class ConfigWrapper(val config: Config) {
  def stringOpt(path: String): Option[String] = {
    if (config.hasPath(path)) Some(config.getString(path))
    else None
  }

  def stringListOpt(path: String): Option[List[String]] = {
    if (config.hasPath(path)) Some(config.getStringList(path).asScala.toList)
    else None
  }
}

object ConfigWrapper {
  implicit def configToWrapper(config: Config): ConfigWrapper = new ConfigWrapper(config)
  implicit def wrapperToConfig(wrapper: ConfigWrapper): Config = wrapper.config
}
