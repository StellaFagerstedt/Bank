package bank
import bank.UsernameExists
import scala.compiletime.ops.double
import bank.getUserbyUsername
import java.time.LocalDateTime
def Start () : Unit = 
    println("Enter Username: Log in, 0: Exit, 1: Create new user , 2: Roll-Back")
    var input = scala.io.StdIn.readLine()
    if input == "0" then sys.exit()
    if input == "1" then CreateUser()
    if input == "2" then RollBack()
    while !UsernameExists(input) do
        println("Username not found")
        Start()
    LoggedOn(getUserbyUsername(input))

def CreateUser(): Unit =
    println("Enter username: ")
    val username = scala.io.StdIn.readLine()
    Users.append(User(username))
    WriteAll()
    Start()

def RollBack() : Unit =
    println("Date : YYYY MM DD")
    val date = scala.io.StdIn.readLine()
    println("Minute and hour : HH MM")
    val time = scala.io.StdIn.readLine()
    val datesplit = date.split(" ")
    val timesplit = time.split(" ")
    val datetime = LocalDateTime.of(datesplit(0).toInt,datesplit(1).toInt,datesplit(2).toInt,timesplit(0).toInt,timesplit(1).toInt)
    rollback(datetime)
    Start()

def LoggedOn(user : User) : Unit =
    println(s"Logged in on ${user.username}")
    user.viewAccounts()
    println("0: log out, 1 : create new account , 2: Address book, Account number: view account, ")
    var input = scala.io.StdIn.readInt()
    if input == 0 then 
        Start()
    else if input == 1 then
        user.createAccount()
        LoggedOn(user)
    else if input == 2 then
        println(user.AddressBook())
        LoggedOn(user)
    while !AccountNumberexists(input.toInt) do
        println("Cannot find account")
        LoggedOn(user)
    getAccountbynumber(input).view()
    


