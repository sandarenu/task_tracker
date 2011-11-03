package org.sansoft.tt.util

object Util {

  def richFormat(string: String, replacement: Map[String, Any]) =
    (string /: replacement) { (res, entry) => res.replaceAll("#\\{%s\\}".format(entry._1), entry._2.toString) }
}

