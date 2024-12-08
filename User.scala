package bank
case class User (username:String, Accounts : collection.mutable.Buffer[Account] = collection.mutable.Buffer.empty[Account]) derives upickle.default.ReadWriter:
    def viewAccounts() : Unit =
        // Shows all accounts and their balance
        Accounts.foreach(u=> println(u.toString))

    def createAccount() : Unit =
        //adds an account to vector then updates the database
        Accounts.append(Account())
        WriteAll()

    def removeAccount(id : Int) : Unit =
        //removes an account with id Int from vector then updates the database
        if Accounts.contains(getAccountbynumber(id)) then
            Accounts.remove(Accounts.indexOf(getAccountbynumber(id)))
        else println("Account does not exist")

    def AddressBook() : String=
        //returns a string with all Users and their accounts
        var AddressBook = "Address book"
        Users.foreach(U => {AddressBook += "\n" + U.username
        AddressBook += ":"
        U.Accounts.foreach(A => AddressBook += "\n \t" + A.accountnumber.toString())})
        AddressBook       



