# DemoBlaze Automation Framework (Selenium + Java) 🚀

![DemoBlaze Logo](https://www.demoblaze.com/image/logo.png)  
*Automated testing framework for DemoBlaze e-commerce site using Selenium WebDriver and Java.*

---

## 📌 **Framework Overview**
This project automates end-to-end testing of [DemoBlaze](https://www.demoblaze.com/) using:
- **Selenium WebDriver** (v4.+) for browser automation  
- **Java** (JDK 11+) as programming language  
- **TestNG** as test runner  
- **Maven** for dependency management  
- **Page Object Model (POM)** design pattern  
- **Extent Reports** for detailed test reporting  

---

## 🏗️ **Project Structure**
```bash
src/
├── main/
│   └── java/
│       ├── pages/          # Page Object Classes (HomePage, CartPage, etc.)
│       ├── utils/          # Helpers (ConfigReader, BrowserUtils)
│       └── managers/       # DriverManager, PageObjectManager
├── test/
│   └── java/
│       ├── testCases/      # Test scripts (LoginTest, CheckoutTest)
│       └── listeners/      # TestNG listeners
resources/
├── config.properties       # Environment configs
├── test-data/              # Test data (JSON/Excel)
test-output/                # TestNG reports & ExtentReports
pom.xml                     # Maven dependencies
