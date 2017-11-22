package bbc.rms.scalambda

import bbc.rms.scalambda.PingPongHandler.{Ping, Pong, toPing, fromPong}
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

import scala.util.{Failure, Success, Try}

class PingPongHandler extends Lambda[Ping, Pong](toPing, fromPong) {
  override def handle(input: Ping, context: Context): Try[Pong] = Success {
    Pong(input.message.reverse)
  }
}

object PingPongHandler {
  case class Ping(message: String)
  case class Pong(message: String)

  import org.json4s._
  import org.json4s.jackson.Serialization.{read, write}

  implicit val formats = DefaultFormats

  val toPing: String => Try[Ping] = { input: String => Try(read[Ping](input)) }
  val fromPong: Pong => Try[String] = { pong: Pong => Try(write(pong)) }
}

abstract class Lambda[Input, Output](
  deserialize: String => Try[Input],
  serialize: Output => Try[String]
) extends RequestHandler[String, String] {

  def handle(input: Input, context: Context): Try[Output]

  final def handleRequest(input: String, context: Context): String = {
    val callHandle: Input => Try[Output] = handle(_, context)

    deserialize(input) flatMap callHandle flatMap serialize match {
      case Failure(error) => throw error
      case Success(output) => output
    }
  }
}
