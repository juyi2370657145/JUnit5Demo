---
marp: true
title: JUni的Unit5的变化
backgroundColor: #fff
backgroundImage: url('https://marp.app/assets/hero-background.svg')
---

<!-- _class: lead -->

#  <!-- fit --> JUint5

---

## JUint5的构成

Junit5的框架主要有三个部分组成分别是：JUnit Platform + JUnit Jupiter + JUnit Vintage3

1. JUnit Platform :

   其主要作用是在 JVM 上启动测试框架。同时支持通过命令行、Gradle 和 Maven 来运行平台。
   
2. JUnit Jupiter:

   这是 Junit5 的核心，可以看作是承载 Junit4 原有功能的演进，包含了 JUnit 5 最新的编程模型和扩展机。

3. JUnit Vintage3

   作为新一代框架，这个模块是对 JUnit3，JUnit4 版本兼容的测试引擎，使旧版本 junit 的⾃动化测试脚本也可以顺畅运行在 Junit5 下。

---

## [注解](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)

| 注解                     | 说明                             |
|------------------------|--------------------------------|
| @Test                  | 标记该方法时测试方法。                    |
| @ParameterizedTest     | 用于参数化测试，需要配合其他注解提供参数源。         |
| @RepeatedTest          | 能够重复执行一个测试多次。                  |
| @TestFactory           | 用于动态化测试。                       |
| @TestTemplate          | 用于模板化测试，类似参数化测试。               |
| @TestClassOrder        | 用于指定测试类执行顺序。                   |
| @TestMethodOrder       | 用于指定测试方法执行顺序。                  |
| @TestInstance          | 用于指定测试时是否创建新的测试实例。             |
| @DisplayName           | 用于设置类或方法在日志中展示的名称。             |
| @DisplayNameGeneration | 用于设置类或方法在日志中展示的名称，有预先配置好的命名策略。 |
| @BeforeEach            | 在所有测试方法前执行。                    |
| @AfterEach             | 在所有测试方法后执行。                    |
| @BeforeAll             | 在所有测试方法前执行，只能注解在static方法上。     |
| @AfterAll              | 在所有测试方法后执行，只能注解在static方法上。     |
| @Nested                | 用于嵌套单元测试。                      |
| @Tag                   | 用于分组测试方法。                      |
| @Disabled              | 标记测试方法，不再执行。                   |
| @Timeout               | 用于标记测试方法超时时间。                  |
| @ExtendWith            | 用于测试应用扩展。                      |
| @RegisterExtension     | 用于注册扩展测试类。                     |
| @TempDir               | 用于创建测试时临时目录。                   |

---

### @Test

标记该方法是测试方法。

``` java
@Test
public void test() {
	// ...
}t
```

---

### @ParameterizedTest

`@ParameterizedTest`配合`@ValueSource`等注解实现参数化测试

`@ValueSource`:指定入参来源，支持八大基础类以及String类型，Class类型

`@NullSource`:提供一个null的入参

`@EnumSource`:提供一个枚举入参

``` java
@ParameterizedTest
@ValueSource(strings = {"one", "two", "three"})
public void parameterizedTest1(String string) {
    System.out.println(string);
    Assertions.assertTrue(StringUtils.isNotBlank(string));
}
```

---

### @RepeatedTest

`@RepeatedTest`,使用该注解允许某个单元测试执行多次。

``` java
@RepeatedTest(10) //表示重复执行10次
public void repeatedTest() {
    Assertions.assertTrue(1 == 1);
}
```

---

### @TestClassOrder

`@TestClassOrder`可以用于指定测试类的执行顺序，使用`@Order`来指定执行顺序。

``` java
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ClassOrder {
    @Nested
    @Order(1)
    class OderTest1{
        @Test
        void test() throws Exception{
            Thread.sleep(1000);
            System.out.println("1");
        }
    }

    @Nested
    @Order(2)
    class OrderTest2{
        @Test
        void test2 (){
            System.out.println("2");
        }
    }
}

```

---

### @TestMethodOrder

`@TestMethodOrder`可以用于指定测试方法的执行顺序。使用`@Order`来指定执行顺序。

``` java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//该注解使用在类上面
public class MethodOrder {
    @Test
    @Order(1)//该注解使用在方法上
    void test() throws Exception{
        Thread.sleep(1000);
        System.out.println("1");
    }
    @Test
    @Order(2)
    void test2() {
        System.out.println("2");
    }
}
```

---

### @TestInstance

此注解是为了减少运行单元测试时创建的对象数量。

### @DisplayName

新增了`@DisplayName`为测试类或者测试方法设置展示名称。

``` java
@DisplayName("测试类展示名")
public class DisplayNameDemo {
    @Test
    @DisplayName("测试方法展示名")
    public void function() {
        // ...
    }
}
```

---

### @DisplayNameGeneration

用于自动生成展示名称，主要有如下四种生成策略：

| **DisplayNameGenerator** | 说明                     |
|--------------------------|------------------------|
| Standard                 | JUnit5发布以来的标准展示名称生成策略。 |
| Simple                   | 移除没有参数的方法的括号。          |
| ReplaceUnderscores       | 替换下划线为空格。              |
| IndicativeSentences      | 将测试和测试类名称拼接来生成展示名称。    |

---

### @BeforeEach和@AfterEach

这两个注解是用于在每个测试方法前后执行。

### @BeforeAll和@AfterAll

这两个注解是在一个测试类执行前后执行，而且需要注解在静态方法上。

---

``` java
public class BeforeAndAfter {
    @Test
    public void test1() {System.out.println("Test1");}
    @Test
    public void test2() {System.out.println("Test2");}
    
    @BeforeEach
    public void beforeEach() {System.out.println("Before Each.");}
    @AfterEach
    public void afterEach() {System.out.println("After Each.");}
    @BeforeAll
    public static void beforeAll() {System.out.println("Before All.");}
    @AfterAll
    public static void afterAll() {System.out.println("After All.");}
}
```

```
Before All.
Before Each.
Test1
After Each.
Before Each.
Test2
After Each.
After All.
```

---

### @Nested

新增内嵌单元测试，使用`@Nested`注解，能够以静态内部成员类的形式对测试用例类进行逻辑分组。

``` java
public class NestedTestDemo {
    
   @Test
    void firstNested() {
        System.out.println("第一层--内嵌单元测试");
    }
    
    @Nested
    class Nested1 {
        
        @Test
        void secondNested() {
            System.out.println("第二层--内嵌单元测试");
        }
        
        @Nested
        class Nested2 {
         	
            @Test
            void thirdNested() {
                System.out.println("第三层--内嵌单元测试");
            }
        }
    }
}
```

---

### @Tag

该注解是用于分组测试，通过`@Tag("tag")` 来标记不同分组，通过在pom.xml中配置要执行的标签来执行测试。

``` java
@Tag("tag1")
class TagDemo {
 
    @Test
    @Tag("tag2")
    void test1() {
        // ...
    }
}
```

---

### @Disabled

该注解是用于标记该测试方法不执行。还可以通过`@Disabled("说明不执行原因。")`

``` java
@Disabled("说明测试不执行原因")
class DisabledClassDemo {
 
    @Test
    void testWillBeSkipped() {
        // ...
    }
}
```

---

### @Timeout

用于表示测试方法运行时间超过指定时间就会返回错误。

``` java
public class TimeOutTest {

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void timeoutTest() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
```

---

## [断言](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html)

| 方法                        | 返回值                 | 说明                                                    |
|---------------------------|---------------------|-------------------------------------------------------|
| assertAll                 | void                | 用于在一个实例中包含多个断言。                                       |
| assertArrayEqual          | void                | 用于断言实际数组与期望数组一致。有多种类型参数重载方法。                          |
| assertDoesNotThrow        | void                | 用于断言没有捕获到任何异常。                                        |
| assertEquals              | void                | 用于断言实际数据与期望数据一致。有多种类型参数重载方法。                          |
| assertFalse               | void                | 用于断言提供参数是否为false。                                     |
| assertInterfaceOf         | void                | 用于断言目标对象是期望类型的实例。                                     |
| assertIterableEquals      | void                | 用于断言两个迭代器长度和内容一致。                                     |
| assertLinesMatch          | void                | 用于断言实际字符串列表于期望字符串列表一致。                                |
| assertNotEquals           | void                | 用于断言实际数据与期望不相等。                                       |
| assertNotNull             | void                | 用于断言实际参数不为null。                                       |
| assertSame                | void                | 用于断言实际对象和期望对象引用地址一致。                                  |
| assertThrows              | T extends Throwable | 用于断言所执行代码块，抛出了期望异常类型。                                 |
| assertThrowsExactly       | T extends Throwable | 用于断言所执行代码块，抛出了完全匹配期望异常类型。                             |
| assertTimeout             | void                | 用于断言Executable执行时间没有超过指定时间。                           |
| assertTimeout             | T                   | 用于断言ThrowingSupplier执行时间没有超过指定时间。                     |
| assertTimeoutPreemptively | void                | 用于断言Executable执行时间没有超过指定时间，指定时间到达立即中断Excutable。       |
| assertTimeoutPreemptively | T                   | 用于断言ThrowingSupplier执行时间没有超过指定时间，指定时间到达立即中断Excutable。 |
| assertTrue                | void                | 用于断言实际值为true。                                         |
| fail                      | V                   | 立即使测试失败。                                              |

---

### assertAll

`assertAll()`可实现1个用例中包含多个断言，遇到断言失败仍然会继续下一个断言；

``` java
	static void assertAll(String heading, Executable... executables)
```

``` java
    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }
```

---

### assertArrayEquals

`assertArrayEquals()`与`assertEquals()`相似，是对数组执行相同的操作,总共有9种类型重载的方法，包括8大基本数据类型数组和Object[]。

``` java
static void assertArrayEquals(int[] expected, int[] actual, Supplier<String> messageSupplier)
```

``` java
    @Test
    void arrayEqualsTest() {
        // 测试会通过
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // 测试不通过，因为元素顺序不一样
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 3, 2}, "Array Equal Test");

        // 测试不通过，因为包含元素长度不一致
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}, () -> "Array Equal Test" + " Fail.");
    }
```

---

### assertDoesNotThrow

`assertDoesNotThrow()`用于断言没有捕获到任何异常。

``` java
static void assertDoesNotThrow(Executable executable, Supplier<String> messageSupplier)
static T assertDoesNotThrow(ThrowingSupplier<T> supplier, Supplier<String> messageSupplier)
```

``` java
    @Test
    void doesNotThrow() {
        assertDoesNotThrow(() -> {
            // 将会抛出ArithmeticException
            // calculator.divide(1, 0);
            int i = calculator.add(1, 2);
            System.out.print(i);
        });
    }
```

---

### assertEquals

`assertEquals`用于比价实际值是否等于期望值，支持8大数据类型和Object类型

``` java
static void assertEquals(int expected, Integer actual, Supplier<String> messageSupplier)
```

``` java
    @Test
    void equalsTest() {
        assertEquals(1, 1);
        assertEquals(1, 1, "失败信息。");
        assertEquals(1, 1, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertFalse

`assertFalse`用于判断提供的参数是否是false。

``` java
static void assertFalse(boolean condition, Supplier<String> messageSupplier)
static void assertFalse(BooleanSupplier booleanSupplier, Supplier<String> messageSupplier)
```

``` java
@Test
    void falseTest() {
        assertFalse(1 > 2);
        assertFalse(1 > 2, "失败信息。");
        assertFalse(1 > 2,() -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertInstanceOf

`assertInstanceOf()`用于断言目标对象是否是期望类型的实例或子类型实例。

``` java
static <T> T assertInstanceOf(Class<T> expectedType, Object actualValue, Supplier<String> messageSupplier)
```

``` java
@Test
    void interfaceTest() {
        Person person = new Person("Junyi", "Zhang");
        assertInstanceOf(Object.class, person);
        assertInstanceOf(Object.class, person,"失败信息。");
        assertInstanceOf(Object.class, person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertIterableEquals

`assertIterableEquals()` 是用于迭代器之间进行比较。

``` java
static void assertIterableEquals(Iterable<?> expected, Iterable<?> actual)
```

``` java
@Test
void iterableEqualsTest() 
{
     Iterable<Integer> listOne = new ArrayList<>(Arrays.asList(1,2,3,4));
     Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1,2,3,4));
     Iterable<Integer> listThree = new ArrayList<>(Arrays.asList(1,2,3));
     Iterable<Integer> listFour = new ArrayList<>(Arrays.asList(1,2,4,3));

    //Test will pass
    assertIterableEquals(listOne, listTwo);

    //Test will fail
    assertIterableEquals(listOne, listThree);

    //Test will fail
    assertIterableEquals(listOne, listFour);
}
```

---

### assertLinesMatch

`assertLinesMatch()`断言期望字符串列表与实际列表相匹配。

``` java
static void assertLinesMatch(List<String> expectedLines, List<String> actualLines);
static void assertLinesMatch(Stream<String> expectedLines, Stream<String> actualLines);
```

``` java
    @Test
    void linesMatchTest() {
        List<String> list1 = Arrays.asList("one", "two", "three");
        List<String> list2 = Arrays.asList("one", "two", "three");

        Stream<String> stream1 = Stream.of("one", "two", "three");
        Stream<String> stream2 = Stream.of("one", "two", "three");

        assertLinesMatch(list1, list2);
        assertLinesMatch(stream1, stream2);
    }
```

---

### assertNotEquals

`assertNotEquals()`作用和`assertEquals()`作用相反，是断言实际值与期望值不相等。

``` java
static void assertNotEquals(float unexpected, Float actual)
static void assertNotEquals(float unexpected, Float actual, String message)
static void assertNotEquals(float unexpected, Float actual, Supplier<String> messageSupplier
```

``` java
    @Test
    void assertNotEqualsTest() {
        assertNotEquals(1, 2);
        assertNotEquals(1, 2, "失败信息。");
        assertNotEquals(1, 2, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertNotNull

`assertNotNull()`用于断言目标对象不为null。

``` java
static void assertNotNull(Object actual)
static void assertNotNull(Object actual, String message)
static voidassertNotNull(Object actual, Supplier<String> messageSupplier)
```

``` java
    @Test
    void notNullTest() {
        assertNotNull(person);
        assertNotNull(person, "失败信息。");
        assertNotNull(person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertSame

`assertSame()`用于判断实际对象和预期对象是否引用同一个对象。

``` java
static void assertSame(Object expected, Object actual)
static void assertSame(Object expected, Object actual, String message)
static void assertSame(Object expected, Object actual, Supplier<String> messageSupplier)
```

``` java
    @Test
    void sameTest() {
        Person personRef = Main.person;
        
        assertSame(personRef, Main.person);
        assertSame(personRef, Main.person, "失败信息。");
        assertSame(personRef, Main.person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### assertThrows

`assertThrows()`用于断言有期望类型异常或期望类型异常的子类异常被捕获。

``` java
static <T extends Throwable> assertThrows(Class<T> expectedType, Executable executable);
```

``` java
	@Test
    void exceptionTesting() {
        Exception exception = assertThrows(Exception.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
```

---

### assertThrowsExactly

`assertThrowsExactly()`与`assertThrows()`类似，不过对于捕获到的异常更加严格，必需匹配指定的异常，不匹配，测试就会失败。

``` java
static <T extends Throwable> assertThrowsExactly(Class<T> expectedType, Executable executable)
```

```
    @Test
    void throwExactly() {
        // 替换异常类型测试将会失败 比如替换为Exception.class
        Exception exception = assertThrowsExactly(ArithmeticException.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
```

---

### assertTimeout

`assertTimeout()`用于给目标代码块指定执行时间，如果运行时间超过指定时间，测试就会失败。

``` java
static void assertTimeout(Duration timeout, Executable executable)
static <T> T assertTimeout(Duration timeout, ThrowingSupplier<T> supplier)
```

``` java
    @Test
    void timeoutTest() {
        // 修改指定时间为100ms，测试就会失败。
        assertTimeout(Duration.ofMillis(300), () -> {
            Thread.sleep(200);
            System.out.println("after sleep");
        });
    }
```

---

### assertTimeoutPreemptively

`assertTimeoutPreemptively()`与`assertTimeout()`功能类似，但是`assertTimeoutPreemptively()`在到达指定时间，测试失败后会立即停止执行目标代码块的剩下内容。

``` java
    @Test
    void timeoutPreemptivelyTest() {
        // 修改指定时间为100ms，测试就会失败，并且控制台不会打印"after sleep"。
        assertTimeoutPreemptively(Duration.ofMillis(300), () -> {
            Thread.sleep(200);
            System.out.println("after sleep");
        });
    }
```

---

### assertTrue

`assertTrue()`与`assertFalse()`功能类似，断言实际值为true

``` java
static void assertTrue(boolean condition)
static void assertTrue(boolean condition, String message)
static void assertTrue(boolean condition, Supplier<String> messageSupplier)
```

``` java
	@Test
    void trueTest() {
        assertTrue(1 == 1);
        assertTrue(1 == 1, "失败信息。");
        assertTrue(1 == 1, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
```

---

### fail

`fail()`会使改测试方法立即失败。

``` java
public static void fail(String message)
public static void fail(Throwable cause)
public static void fail(String message, Throwable cause)
public static void fail(Supplier<String> messageSupplier)
```

---

![bg 100% fit](https://www.haoy99.com/FileUpload/2019-02/ThanksXie_Xie_Guan_S-151726_101_title.jpg)
