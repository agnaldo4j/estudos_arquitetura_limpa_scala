package com.sizebay.algorithm.restapi.console

import java.util.Scanner
import javax.script.ScriptEngineManager

object Boot {
  def main(args: Array[String]) {
    def pathConfig = if (args.length > 0) args(0) else "/usr/share/sizebay/sizebayRestAPIHarnessSystem.conf"
    System.setProperty("scala.usejavacp", "true")

    val engine = new ScriptEngineManager().getEngineByName("scala")

    engine.put("consoleCommands", new ConsoleCommands(pathConfig))
    engine.eval("val shell:com.sizebay.algorithm.restapi.console.ConsoleCommands = consoleCommands.asInstanceOf[com.sizebay.algorithm.restapi.console.ConsoleCommands]")
    engine.eval("val sendMessageToAnalysis = shell.sendMessageToAnalysis(_:String, _:String, _: String)")
    engine.eval("val sendMessageToRecommendation = shell.sendMessageToRecommendation(_:String, _:String, _: String)")
    engine.eval("val sendMessageToDeduction = shell.sendMessageToDeduction(_: String, _: Int, _: Int, _: Int, _: Int, _: Int, _: Int)")


    val sc = new Scanner(System.in)
    System.out.println("cmd:")

    while(sc.hasNextLine()) {
      try {
        val strOut = engine.eval(sc.next())
        if(strOut != null) println("--> "+strOut+"\ncmd:")
      } catch {
        case e: Exception => e.printStackTrace()
      }
    }
  }
}

