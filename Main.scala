//contains actual states
package bank
import scala.util.Random
import java.time.LocalDateTime
val Users = collection.mutable.Buffer.empty[User]
def idgenerator(): Int =
    Random.nextInt(1000)

def AccountNumberexists(number: Int) : Boolean =
    var check = false
    Users.foreach(_.Accounts.foreach(Account => if Account.accountnumber == number then check = true))
    check

def getAccountbynumber(number: Int): Account =
    var account = Account()
    Users.foreach(_.Accounts.foreach(Account => if Account.accountnumber == number then 
        account = Account))
    account

def roundoff(number: Float): Float =
    BigDecimal(number).setScale(2, BigDecimal.RoundingMode.HALF_UP).toFloat

def UsernameExists(username: String) : Boolean =
    var check = false
    Users.foreach(u => if u.username == username then check = true)
    check 

def getUserbyAccount(account: Account) : User =
    var user = User("")
    Users.foreach(User => User.Accounts.foreach(Account => if Account == account then
        user=User))
    user

def updateUsers (): Unit =
    ReadAll().foreach(u => Users.append(u))
def getUserbyUsername(username: String) : User =
    var user = User("")
    Users.foreach(User => if User.username == username then
        user=User)
    user
def rollback(datetime : LocalDateTime) =
    val rollbackdeath = collection.mutable.Buffer.empty[transactions]
    Users.foreach(_.Accounts.foreach(A => 
        A.Transactions.foreach(t => 
            if t.getTime().isAfter(datetime) then 
                rollbackdeath.append(t))
        A.Transactions--=rollbackdeath
        A.update()))
    println(s"Rolled back to ${datetime.toLocalDate().toString()}")


object Main:
    def main(args: Array[String]): Unit =
        updateUsers()
        Start()
