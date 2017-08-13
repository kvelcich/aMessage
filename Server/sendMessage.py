import applescript

# the AppleScript takes two parameters: targetBuddyPhone and targetMessage
# Requires Python Wrapper for AppleScript
# Download it here: https://pypi.python.org/pypi/py-applescript

script = applescript.AppleScript('''
    on run {targetBuddyPhone, targetMessage}
        tell application "Messages"
            set targetService to 1st service whose service type = iMessage
            set targetBuddy to buddy targetBuddyPhone of targetService
            send targetMessage to targetBuddy
        end tell
    end run ''')

# Takes in two parameters of type string
# phone_number:     phone number of person you wish to send message to
# message:          content of message
def sendMessage(phone_number, message):
    script.run(phone_number, message)

# example - this data will eventaully come from the server POST request
my_phone = '9712793494'
message = 'hola hola!'

sendMessage(my_phone, message)
