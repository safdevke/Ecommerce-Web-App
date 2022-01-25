# Ecommerce-Web-App

An attempt at implementing a working e-commerce website. 


# Motivation

The sole purpose of making this project is to learn web app development using Java as the backend, write tests using Selenium and set up a server using Docker to host it.

# Setup

Make sure you have WildFly server and MySQL installed. Download the mysql connector jar file version 8.0.26 from [here](https://jar-download.com/artifacts/mysql/mysql-connector-java)

Create a folder main inside another folder mysql and place the jar file plus a module.xml file with the following content

Your folder structure should look like this

```bash
mysql/main/{.xml .jar files}
```

module.xml
```xml

<module xmlns="urn:jboss:module:1.1" name="com.mysql">
  <resources>
     <resource-root path="mysql-connector-java-8.0.26.jar"/>              
  </resources>
  <dependencies>
     <module name="javax.api"/>
     <module name="javax.transaction.api"/>
  </dependencies>
</module>

```

Copy the mysql folder to your WildFly installation path. Place it inside com

```
{JBOSS_HOME}/modules/system/layers/base/com
```

Lastly, add a datasource to your standalone.xml located at

```
{JBOSS_HOME}/standalone/configuration
```

```xml

<datasource jndi-name="java:jboss/datasources/ecommerce" pool-name="ecommerce" enabled="true" use-java-context="true" statistics-enabled="true">
    <connection-url>jdbc:mysql://localhost:3306/ecommerce?useSSL=false</connection-url>
    <driver>mysql</driver>
        <pool>
            <max-pool-size>100</max-pool-size>
        </pool>
        <security>
            <user-name>db_user</user-name>
            <password>db_password</password>
        </security>
</datasource>

```

```xml

<driver name="mysql" module="com.mysql">
    <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
</driver>

```

Create a database ecommerce using your MySQL db_user. Start WildFly in standalone mode.

# Usage

A live demo is available at [omoka.ml](http://omoka.ml).
# Improvements

<li> Add checkout and tracking capabilities
<li> Complete Selenium tests 
