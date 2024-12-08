package bank

def ReadAll() : collection.mutable.Buffer[User] =
    var buffer = collection.mutable.Buffer.empty[User]
    if os.exists(os.pwd / "data.json") then buffer = upickle.default.read[collection.mutable.Buffer[User]](os.read(os.pwd / "data.json"))
    buffer
def WriteAll() : Unit =
    os.remove(os.pwd / "data.json")
    os.write(os.pwd / "data.json", upickle.default.write(Users))
