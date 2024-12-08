package bank
import java.time.LocalDateTime
import scala.math.BigDecimal
import upickle.default.*

sealed trait transactions () derives ReadWriter: //flagged
    def makeTransaction() : Unit 
    def addTransaction(): Unit
    def getTime() : LocalDateTime 
case class transaction(sendernumber: Int, recievernumber : Int, amount : Float) extends transactions:
    val time : LocalDateTime = LocalDateTime.now()
    val sender = getAccountbynumber(sendernumber)
    val reciever = getAccountbynumber(recievernumber)
    def getTime() : LocalDateTime = time
    def addTransaction(): Unit =
        sender.Transactions.append(this)
        reciever.Transactions.append(this)
    def makeTransaction() : Unit =
        //updates accounts and updates database
        reciever.balance= reciever.balance + amount //flagged
        sender.balance = sender.balance - amount 
    override def toString(): String = 
        //Prettifies a transaction for transaction history
        (s"On ${time.toLocalDate().toString()} ${sendernumber} sent ${roundoff(amount)} to ${recievernumber}")

case class withdrawal(accountnumber: Int, amount: Float) extends transactions:
    val time : LocalDateTime = LocalDateTime.now()
    val account = getAccountbynumber(accountnumber)
    def getTime() : LocalDateTime = time
    def addTransaction(): Unit =
        account.Transactions.append(this)
    def makeTransaction() : Unit =
        account.balance = account.balance - amount
    override def toString(): String =
        (s"On ${time.toLocalDate().toString()} ${roundoff(amount)} was withdrawn from ${account.accountnumber}")

case class deposit(accountnumber: Int, amount: Float) extends transactions:
    val time : LocalDateTime = LocalDateTime.now()
    val account = getAccountbynumber(accountnumber)
    def getTime() : LocalDateTime = time
    def addTransaction(): Unit =
        account.Transactions.append(this)
    def makeTransaction(): Unit = 
        account.balance = account.balance + amount
    override def toString(): String = 
        (s"On ${time.toLocalDate().toString()} ${roundoff(amount)} was deposited to ${account.accountnumber}")