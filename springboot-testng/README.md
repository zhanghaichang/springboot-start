# 单元测试利器TestNG

TestNG是一个测试框架，其灵感来自JUnit和NUnit的，但引入了一些新的功能，使其功能更强大，使用更方便。

TestNG是一个开源自动化测试框架;TestNG表示下一代。 TestNG是类似于JUnit（特别是JUnit 4），但它不是一个JUnit扩展。它的灵感来源于JUnit。它的目的是优于JUnit的，尤其是当测试集成的类。 TestNG的创造者是Cedric Beust（塞德里克·博伊斯特）

TestNG消除了大部分的旧框架的限制，使开发人员能够编写更加灵活和强大的测试。 因为它在很大程度上借鉴了Java注解（JDK5.0引入的）来定义的测试，它也可以告诉你如何使用这个新功能在真实的Java语言生产环境中。

[官网](http://testng.org/doc/index.html)

[TestNG工具使用方法](https://www.cnblogs.com/TankXiao/p/3888070.html#simpleTest)

## 注解 描述

**@BeforeSuite**

注解的方法将只运行一次，运行所有测试前此套件中。

**@AfterSuite**

注解的方法将只运行一次此套件中的所有测试都运行之后。

**@BeforeClass**

注解的方法将只运行一次先行先试在当前类中的方法调用。

**@AfterClass**

注解的方法将只运行一次后已经运行在当前类中的所有测试方法。

**@BeforeTest**

注解的方法将被运行之前的任何测试方法属于内部类的 <test>标签的运行。

**@AfterTest**

注解的方法将被运行后，所有的测试方法，属于内部类的<test>标签的运行。

**@BeforeGroups**

组的列表，这种配置方法将之前运行。此方法是保证在运行属于任何这些组第一个测试方法，该方法被调用。

**@AfterGroups**

组的名单，这种配置方法后，将运行。此方法是保证运行后不久，最后的测试方法，该方法属于任何这些组被调用。

**@BeforeMethod**

注解的方法将每个测试方法之前运行。

**@AfterMethod**

被注释的方法将被运行后，每个测试方法。

**@DataProvider**

标志着一个方法，提供数据的一个测试方法。注解的方法必须返回一个Object\[\] \[\]，其中每个对象\[\]的测试方法的参数列表中可以分配。 该@Test 方法，希望从这个DataProvider的接收数据，需要使用一个dataProvider名称等于这个注解的名字。

**@Factory**

作为一个工厂，返回TestNG的测试类的对象将被用于标记的方法。该方法必须返回Object\[\]。

**@Listeners**

定义一个测试类的监听器。

**@Parameters**

介绍如何将参数传递给@Test方法。

**@Test**

标记一个类或方法作为测试的一部分。