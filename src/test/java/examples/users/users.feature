Feature: sample karate test script
  for help, see: https://github.com/karatelabs/karate/wiki/IDE-Support

  Background:
    * url 'https://jsonplaceholder.typicode.com'
    * def startmock = (mockName) => karate.start({mock:mockName,port:mockServerPort}).port
    * def mockServerUrl = 'http://localhost:'+ startmock('localTestServer.mock') +'/api/response'

  Scenario: hit async api
    * def JavaDemo = Java.type('examples.users.UsersRunner')
    * def result = JavaDemo.makeApiCall(mockServerUrl)
    * print "listening"
    * listen 1500
    * print listenResult
    * match listenResult == 'hello'


  