# Webcrawler
Simple web crawler which extract links and images from a give website 
-------------------------------------------

webcrawler is developed using Spring boot.

Execution steps:
```
mvn package

java -jar WebCrawler-0.0.1-SNAPSHOT.jar --webcrawler.domain="http://www.example.com"
```
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

Example Output file sitemap.json
```json
{
  "sitemap" : {
    "loc" : [ "http://localhost:8080/contact/C3.html", 
              "http://localhost:8080/product/P11.html",
              "http://localhost:8080/Product.html"],
    "image" : [ "http://localhost:8080/about/Logo.png" ]
  }
}
```

# TestSite
TestSite is demo website which contains static webpages, build using spring boot

to exceute on command line
```
mvn package

java -jar TestSite-0.0.1-SNAPSHOT.jar
```
It will launched test website on port 8080
http://localhost:8080

-------

To execute both application together
* Execute below command
java -jar TestSite-0.0.1-SNAPSHOT.jar
java -jar WebCrawler-0.0.1-SNAPSHOT.jar --webcrawler.domain="http://localhost:8080"
