# AppleScript to catch iMessage and start python script
# passing the messsage as a command line argument

using terms from application "Messages"
    # when message is received
    on message received theMessage from theBuddy for theChat
    # call python ('do shell script' does not inherit bash environments)
    # >> explicitly source the profile
    # and perfrom python script at given directory - change this to /path/to/python_script.py
    # with iMessage in quoted form (handles spaces between words)
    do shell script "/usr/bin/python /Users/esai/Desktop/print.py " & theMessage's quoted form
end message received

on message sent theMessage for theChat
    
end message sent

on chat room message received theMessage from theBuddy for theChat
    
end chat room message received

on active chat message received theMessage
    
end active chat message received

on addressed chat room message received theMessage from theBuddy for theChat
    
end addressed chat room message received

on addressed message received theMessage from theBuddy for theChat
    
end addressed message received

on av chat started
    
end av chat started

on av chat ended
    
end av chat ended

on login finished for theService
    
end login finished

on logout finished for theService
    
end logout finished

on buddy became available theBuddy
    
end buddy became available

on buddy became unavailable theBuddy
    
end buddy became unavailable

on completed file transfer
    
end completed file transfer

end using terms from
