#Server to serve sample json.

import tornado
import tornado.ioloop
import tornado.web
from bson import json_util
import os, uuid
import sys
import json
#Tornado Libraries
from tornado.ioloop import IOLoop
from tornado.escape import json_encode
from tornado.web import RequestHandler, Application, asynchronous
from tornado.httpserver import HTTPServer
from tornado.httpclient import AsyncHTTPClient
from tornado.gen import engine, Task
from tornado.web import RequestHandler
from tornado import gen

from bson.objectid import ObjectId
#Other Libraries

import sqlite3
import json
import requests
import os
import urllib2
import re
import time
import datetime
import motor
from motor import MotorClient


db = MotorClient()['weight']

class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.write(json.dumps(dict(status="success", message='API working.'),indent = 4))


#Class to add data from hardware.
class addDataFromMachineHandler(RequestHandler):
	@gen.coroutine
	def get(self):
		weight = float(self.get_argument('weight',0))
		airQuality = float(self.get_argument('air_quality',0))
		timeStamp = datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S')
		writeData = {'weight':weight, 'airQuality':airQuality,'time':timeStamp}
		db = self.settings['db']
		result = yield db.logs.insert(writeData)
		self.write(json.dumps(dict(status="success", message='User data logged successfully'),indent = 4))
		self.flush()

class dbHandler(RequestHandler):
	@gen.coroutine
	def get(self):
		db = self.settings['db']
		data = []
		cursor = db.logs.find()
		while (yield cursor.fetch_next):
	         document = cursor.next_object()
	         data.append(json.loads(json_util.dumps(document)))
		self.write(json.dumps(dict(data=data)))

def make_app():
    return tornado.web.Application([
        (r"/", MainHandler),
        (r"/addData", addDataFromMachineHandler),
        (r"/show", dbHandler)
    ], db=db, debug=True)
##########################################################################################################
#												App Run
##########################################################################################################

if __name__ == "__main__":
    app = make_app()
    app.listen(5000)
    tornado.ioloop.IOLoop.current().start()


