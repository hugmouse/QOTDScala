import java.net.ServerSocket
import scala.util.Random

object Quote:
  val Quotes = List(
    "Play Team Fortress 2, it's free!\r\n",
    "Pay for Team Fortress, it's not free!\r\n"
  )
  def giveMeOne(random: Random): String = Quotes(random.nextInt(Quotes.length))

class QuoteServer:
  var serverSocket: ServerSocket = null
  val q = Quote
  val r = Random

  def start(port: Int): Unit =
    serverSocket = new ServerSocket(port)
    while !serverSocket.isClosed do
      val acceptedConn = serverSocket.accept
      acceptedConn.getOutputStream.write(q.giveMeOne(r).getBytes)
      acceptedConn.close()

  def stop(): Unit = serverSocket.close()

object QOTD:
  @main def main(): Unit =
    val server = new QuoteServer
    server.start(17)