# Dolphin Platform [![Travis Build](https://travis-ci.org/canoo/dolphin-platform.svg?branch=master)](https://travis-ci.org/canoo/dolphin-platform) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.canoo.dolphin-platform/dolphin-platform-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.canoo.dolphin-platform/dolphin-platform-core)
                                                                                                                               

This repository contains all Java related sources of the Dolphin Platform. Clients for other languages can be found in seperate repositories ([JavaScript](https://github.com/canoo/dolphin-platform-js), AngularJS, [Polymer](https://github.com/canoo/dolphin-platform-polymer)).

![Dolphin Platform Logo](http://www.guigarage.com/wordpress/wp-content/uploads/2015/10/logo.png)

The Dolphin Platform is a framework that implements the presentation model pattern and provides a modern way to create enterprise applications. The Platform provides several client implementations that all can be used in combination with a general sever API.

![Several clients](http://www.dolphin-platform.io/assets/img/features/clients.png)


By doing so you can create enterprise application with a single server and several desktop, web and mobile client implementations. Here the Dolphin Platforms define a mechanism to automatically snchronize models between the server and the client.

![Model sync](http://www.dolphin-platform.io/assets/img/features/pm1.png)


For more information visit [our website](http://www.dolphin-platform.io).

## How to use it
You can simply integrate Dolphin Platform in a Spring based application. To do so you only need to add our Spring plugin:
```xml
<dependency>
    <groupId>com.canoo.dolphin-platform</groupId>
    <artifactId>dolphin-platform-server-spring</artifactId>
    <version>0.8.4</version>
</dependency>
```

Next to Spring we provide support for JavaEE. To do so you only need to add our JavaEE plugin:
```xml
<dependency>
    <groupId>com.canoo.dolphin-platform</groupId>
    <artifactId>dolphin-platform-server-javaee</artifactId>
    <version>0.8.4</version>
</dependency>
```


For a JavaFX based client you need to add the following dependency:
```xml
<dependency>
    <groupId>com.canoo.dolphin-platform</groupId>
    <artifactId>dolphin-platform-client-javafx</artifactId>
    <version>0.8.4</version>
</dependency>
```

Next to JavaFX you can use Dolphin Platform in any web client. You can find additonal information in the readme of the client libraries: ([JavaScript](https://github.com/canoo/dolphin-platform-js) or [Polymer](https://github.com/canoo/dolphin-platform-polymer))

In addition you can use [our Maven archetype](http://www.guigarage.com/2015/12/dolphin-platform-jumpstart/) to create a complete server-client-project based on Dolphin Platform.
If you want to create your new project from command line by using an archetype you can simply call this Maven command and select one of the shown Dolphin Platform archetypes:
```shell
mvn archetype:generate -Dfilter=com.canoo.dolphin-platform:
```
Currently the projects contains only a JavaFX based client but it’s planned to add a Polymer based client to the archetypes with the next release.

A complete "Getting started" documentation can be found [here](http://www.dolphin-platform.io/documentation/getting-started.html).

## Useful links
* [Dolphin Platform website](http://www.dolphin-platform.io)
* [Getting started](http://www.dolphin-platform.io/documentation/getting-started.html)
* [Tutorial](http://www.dolphin-platform.io/documentation/tutorial.html)
* [JavaDoc](http://www.dolphin-platform.io/javadoc/index.html)
* [Dolphin Platform @ Twitter](https://twitter.com/DolphinPlatform)
* [StackOverflow](http://stackoverflow.com/questions/tagged/dolphin-platform)
* [Blog Posts](http://www.guigarage.com/2015/10/dolphin-platform-a-sneak-peek/)
* [JavaScript Github Repo](https://github.com/canoo/dolphin-platform-js)
* [Polymer Github Repo](https://github.com/canoo/dolphin-platform-polymer)
* [Spring Boot based Maven archetype](https://github.com/canoo/dolphin-platform-spring-boot-archetype)
* [KumuluzEE based Maven archetype](https://github.com/canoo/dolphin-platform-kumuluz-archetype)
* [Lazybones templates for Dolphin Platform](https://github.com/canoo/dolphin-platform-lazybones-templates)
