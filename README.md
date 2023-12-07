# kabusapi-wsserver
Example of a WebSocket server simulating the kabu station PUSH API.

## Requirements

Building the WebSocket server application requires:
1. Java 1.7+
2. Maven 3.8.x+
3. Dependent Maven Projects (Not registered in public repositories)
    1. https://github.com/hiuchida/kabusapi-client-ex
        1. https://github.com/hiuchida/kabusapi-client
        2. https://github.com/hiuchida/kabusapi-enums

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

## Getting Started

Start your server as an simple java application.

```shell
mvn jetty:run
```

For Windows, run the batch file to suppress the following message when Ctrl+C is pressed.

```shell
run.bat
```

```
バッチ ジョブを終了しますか (Y/N)?
Terminate batch job (Y/N)?
```

To try WebSocket processing, run the following project

https://github.com/hiuchida/kabusapi-wsclient

## Restrictions

The following servers use the same port number (18080) and cannot run at the same time.

- https://github.com/hiuchida/kabusapi-server
  (kabusapi-server\src\main\resources\application.properties: server.port=18080)
- https://github.com/hiuchida/kabusapi-wsserver
  (kabusapi-wsserver\pom.xml: &lt;port>18080&lt;/port>)
