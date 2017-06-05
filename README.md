# Futures demo
Apart from the tests there's both a Spring Web and Jersey server available, though not at the same time.
Uncomment the @Configuration attribute in JerseyConfig to enable Jersey, but note that when Jersey is enabled the spring endpoints won't work.

The endpoints will all wait one second before responding, and are at:
* http://localhost:8090/spring/sync
* http://localhost:8090/spring/async
* http://localhost:8090/jersey/sync
* http://localhost:8090/jersey/async

There are JMeter *.jmx files for testing the endpoints, with 1000 threads each. You should see some difference.