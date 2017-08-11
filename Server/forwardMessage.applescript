# Will forward incoming iMessage to number provided
using terms from application "Messages"
    
    # when a message (theMessage) is received, do the following
	on message received theMessage from theBuddy for theChat
        # return true
        
        # replace 'X..' with number to send to
        set targetBuddyPhone to "XXXXXXXXXX"
        # set the received message to targetMessage (what it sends)
        set targetMessage to theMessage
        # send via iMessage
        set targetService to 1st service whose service type = iMessage
        set targetBuddy to buddy targetBuddyPhone of targetService
        # send iMessage
        send targetMessage to targetBuddy
        
        
    end message received

	on message sent theMessage for theChat
		
	end message sent
	
    # on message received theMessage from theBuddy for theChat
    # end message received

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
