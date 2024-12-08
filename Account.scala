package bank
import scala.util.Random
import scala.compiletime.ops.double
case class Account( accountnumber: Int = angenerator(), Transactions: collection.mutable.Buffer[transactions] = collection.mutable.Buffer.empty[transactions]) derives upickle.default.ReadWriter: //flagged 
    var balance: Float = 0
    def transactionHistory (): Unit =
        //prints a collection.mutable.Buffer[transaction]
        Transactions.foreach(t => println(t.toString()))
    def update (): Unit =
        balance = 0
        Transactions.foreach(_.makeTransaction())
        WriteAll()
    def view (): Unit =
        update()
        println(this.toString())
        println("0: Deposit, 1: Withdrawal, 2: Transaction, 3: View Transaction History, 4: Exit")
        var input = scala.io.StdIn.readInt()
        input match
            case 0 => {
                //makes a deposit
                println("Enter amount to deposit")
                var amount = scala.io.StdIn.readFloat()
                deposit(this.accountnumber, amount).addTransaction()
                view()
            }
            case 1 => {
                //makes a withdrawal
                println("Enter amount to withdraw")
                var amount = scala.io.StdIn.readFloat()
                withdrawal(this.accountnumber, amount).addTransaction()
                view()
            }
            case 2 => {
                //checks that sender exits then makes a transaction                println("Enter recievers account number")
                var recievernumber = scala.io.StdIn.readInt()
                var input = -1
                while !AccountNumberexists(recievernumber)|| input == 0 do
                    println("Account number doesn't exist, enter a valid number or 0 to exit")
                    recievernumber = scala.io.StdIn.readInt()
                    if recievernumber == 0 then 
                        input = 0
                        view()
                println("Enter Amount to tranfer")
                var amount = scala.io.StdIn.readFloat()
                transaction(this.accountnumber, recievernumber, amount).addTransaction()
                view()
            }
            case 3 => {
                transactionHistory()
                view()}
            case 4 => LoggedOn(getUserbyAccount(this))
            case _ => {
                println("invalid input")
                view()
            }
    override def toString(): String = 
        (s"Account $accountnumber : ${roundoff(balance)}")

def angenerator(): Int =
    var i = 1000
    while AccountNumberexists(i) do
        i = i + 1
    i
