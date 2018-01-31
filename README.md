# Webcrawler
Simple web crawler which extract links and images from a give website 
-------------------------------------------

webcrawler is developed using Spring boot.

Execution steps:
mvn package

java -jar WebCrawler-0.0.1-SNAPSHOT.jar --webcrawler.domain="http://www.example.com"

It will generate two output files
* sitemap.json    (output sitemap file which contains links)
* webcrawler.log  (Webcrawler application logs files) 

This application is using external lib Jsoup for parsing HTML pages


```xml
		<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
		<dependency>
			<groupId>org.jsoup</groupId>
			<artifactId>jsoup</artifactId>
			<version>1.8.3</version>
		</dependency>
```