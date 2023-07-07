# kabusapi-wsserver
Example of a WebSocket server simulating the kabu station PUSH API.

## Requirements

Building the WebSocket application requires:
1. Java 1.7+
2. Maven
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

To try WebSocket processing, run the following project

https://github.com/hiuchida/kabusapi-wsclient
