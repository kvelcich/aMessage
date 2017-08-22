from flask import Flask
app = Flask(__name__)

@app.route("/")
def main():
    return "Flask server is running..."
 
@app.route("/new_message", methods=['POST'])
def new_message():
	return "Sending new message..."
