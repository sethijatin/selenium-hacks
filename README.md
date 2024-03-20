
[![](https://jitpack.io/v/sethijatin/selenium-hacks.svg)](https://jitpack.io/#sethijatin/selenium-hacks)
# Selenium Hacks
This project aims to produce functionalities that make Selenium code more accessible and less flaky. Follow this page to view the built-in features. You may post common problems as issues, which will then be added to a roadmap and continue to be built.

## Auto Waiting
Several new libraries have made a point of automating waits. We intend to automate this first feature, as it removes a lot of unnecessary code from the actual page objects. This has been achieved by using WebDriver Listeners from Selenium 4. Upgrading from `Selenium 3` to `Selenium 4` should be easy.

**Usage:**


     WebDriver driver = new EnableWaitsOnDriver(<WebDrive Instance>).apply();  

| Event | Present | Visible | Enabled | Clickable | Stable |  
|--|--|--|--|--|--|  
| **Find Element** | Yes | No | No | No | No |  
| **Clear** | No | Yes | Yes | Yes | Yes |  
| **Click** | No | Yes | Yes | Yes | Yes |  
| **Perform Action** | No | Yes | Yes | Yes | Yes |  
| **Send Keys** | No | Yes | Yes | Yes | Yes |  
| **Submit** | No | Yes | Yes | Yes | Yes |

## Use In Your Project
Add the following to your pom.xml

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
    <dependency>
   	    <groupId>com.github.sethijatin</groupId>
   	    <artifactId>selenium-hacks</artifactId>
   	    <version>0.0.1-A5</version>
   	</dependency>

For other build tools, navigate to [Jitpack](https://jitpack.io/#sethijatin/selenium-hacks) for more details.