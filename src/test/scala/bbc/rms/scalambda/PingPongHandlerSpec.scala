package bbc.rms.scalambda

import com.amazonaws.services.lambda.runtime.{Context, LambdaLogger}
import org.specs2.mock.Mockito
import org.specs2.mutable.Specification

class PingPongHandlerSpec extends Specification with Mockito {

  "The PingPongHandler" should {
    "reverse the input" in {
      val pingPongHandler = new PingPongHandler

      val logger = mock[LambdaLogger]
      val context = mock[Context]
      context.getLogger returns logger

      val request =
        """
          |{ "message": "poing" }
        """.stripMargin

      pingPongHandler.handleRequest(request, context) must be equalTo """{"message":"gniop"}"""
    }
  }
}
