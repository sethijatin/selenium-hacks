# Selenium Hacks
This project aims to produce functionalities that make Selenium code easier and less flaky. Follow this page to view the built-in features. You may continue to post common problems as issues, which will then be added to a roadmap and continue to be built.

## Auto Waiting
A number of new libraries have made a point of automating waits. We intend to automate this first feature, as it removes a lot of unnecessary code from the actual page objects. This has been achieved by using WebDriver Listeners from Selenium 4. Upgrading from `Selenium 3` to `Selenium 4` should be fairly easy.

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