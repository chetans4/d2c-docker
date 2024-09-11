from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello_world():
    print("running root")
    return 'Hello, World! Running ...'



# pip install Flask
# set FLASK_APP=hello.py
# flask run
# http://127.0.0.1:5000/
