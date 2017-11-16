package bbc.rms.scalambda

import bbc.rms.scalambda.PingPongHandler.{Ping, Pong}
import com.amazonaws.services.lambda.runtime.Context
import io.circe.generic.auto._
import io.github.mkotsur.aws.handler.Lambda
import io.github.mkotsur.aws.handler.Lambda._

class PingPongHandler extends Lambda[Ping, Pong] {
  override protected def handle(ping: Ping, context: Context) = {
    val logger = context.getLogger
    logger.log("Logging from the Ping Pong lambda")
    Right(Pong(ping.inputMsg.reverse))
  }
}

object PingPongHandler {
  case class Ping(inputMsg: String)
  case class Pong(outputMsg: String)
}
