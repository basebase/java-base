### Java线程
主要快速复习Java多线程基础知识, 本小结主要包含如下内容
1. 何为线程: 单线程、多线程、Thread类、run方法、start方法
2. 启动线程: Thread类、Runnable接口
3. 线程暂停: sleep方法
4. 线程互斥: synchronized方法、synchronized语句、锁
5. 线程协作: wait方法、notify方法、notifyAll方法


#### 何为线程
***明为跟踪处理流程, 实则跟踪线程***

##### 单线程
当我们在阅读程序, 我们总是按照程序的处理顺序阅读, 无论中间是调用了某个方法还是执行了for循环亦或者其它复杂的业务流程, 程序调用可以曲折蜿蜒但是
我们阅读(又或是执行)的顺序依旧是一条直线不会出现另外一条岔道, 对于这样的程序, 我们可以称之为***单线程程序***

单线程程序永远只有一处再执行, 我们可以非常快速的回答对方, "瞧, 程序已经运行到这了"

单线程程序非常简单, 甚至于我们编写一个Hello, World程序都是一个单线程程序, 虽然程序主体只有一个主线程(main thread), 但实际上Java还会在后台
启动GC(垃圾回收), GUI等相关线程, 所以严格来说运行任何一个程序都不仅仅只有一个线程在运行, 只不过 我们没有感知罢了。

##### 多线程
由多个线程组成的程序可以称为***多线程程序***, 我们在阅读(又或是)多个线程执行时, 我们在回答对方时需要知道线程1运行到这了, 线程2运行到那了, 如果有更多的线程
在运行, 我们需要指出更多的运行情况, 此时程序就不仅仅只是一条线走到底, 而是会出现岔道。

典型的多线程程序可以有下面一些参考:

1. GUI程序, 用户使用文本工具编辑较大的文件时, 执行了查找操作, 当文本工具执行查找时, 屏幕上可以显示"停止查找"按钮, 用户可以随时停止查找。此时可以使用多线程。

   (1) 执行查找
   (2) 显示按钮, 停止查找

两个操作分别使用不同的线程执行, (1)的操作线程专门执行查找, 而(2)的操作线程专门执行GUI操作


2. 耗时的I/O操作, 文件与网络I/O处理都非常耗时。如果在I/O处理期间, 程序几乎无法执行其它计算, 性能因此会下降。这种情况也可以使用多线程来处理。
   可以把I/O处理的线程和其它计算线程分开, 那么在I/O阻塞期间, 程序还可以执行其它计算

##### Thread类start与run方法
任何一个程序都是通过主线程启动, 然后通过主线程启动一个新的线程, 我们可以使用Thread类来启动一个新线程。不过Thread类中有一个run方法是需要我们重写的
新线程启动后会自动调用run方法, 当run方法执行结束时, 线程也会终止。

***我们通过start方法来启动一个线程, 而不是调用run方法, 当然run方法也是可以调用的, 只不过调用它并不会启动新的线程***
start方法主要做了两件事:
1. 启动新线程
2. 调用run方法

```java
public class Main {
   public static void main(String[] args) {
      Thread t = new MyThread();
      // 主线程启动一个新线程
      t.start();
      for (int i = 0; i < 100; i++) {
         //...
      }
   }
}
```

通过这个例子, 我们启动一个新的线程, 可以是循环输出也可以是其它计算操作, 由于程序是并发执行的, 所以结果或许是交织在一起输出, 计算可能会出错。对于这些问题
我们会在后面继续介绍与学习。

#### 启动线程
启动线程的方式有两种:
1. 继承Thread类
2. 实现Runnable接口


##### 通过Thread启动线程
```java

public class Main2 {
    public static void main(String[] args) {
        PrintThread t1 = new PrintThread();
        PrintThread t2 = new PrintThread("H1");
        // 只有调用start方法才算是启动一个线程
        t2.start();
        t1.setMessage("H@");
//        t1.start();
    }
}
```
上面这个例子通过继承Thread类实现动态输出传入的字符串内容, 我们创建两个线程对象实例, 并调用其中一个对象实例的start方法, 程序最终只会输出"H1"
因为***创建对象(PrintThread)实例和启动对象实例线程***是两个不同的处理。也就是说即使创建了实例, 但是如果不去调用执行start方法, 线程依旧不会被启动。

有一个点需要注意的是: PrintThread实例和线程不是同一个东西。即使创建了PrintThread实例, 线程也不会启动, 而且就算线程终止了, PrintThread实例也不会立即消失。

主线程在main方法中启动两个线程, 随后main方法结束, 主线程也随之终止。但是整个程序并不会终止, 因为启动的线程在执行完成之前是不会终止的。
直到所有的线程都终止后, 程序才会终止。也就是说, 当我们启动的线程结束, 程序才会终止。

##### 通过Runnable启动线程

```java
public class Printer implements Runnable {
   public void run() { /*...*/ }
}
public class Main3 {
   public static void main(String[] args) {
      Printer p = new Printer("msg");
      // 使用Thread包装后启动
      Thread t1 = new Thread(p);
      t1.start();
   }
}
```

我们只需要实现Runnable接口并实现run方法即可, 但是实现Runnable接口并没有继承Thread类的start方法只有一个run方法, 这是无法启动一个线程的, 
所有我么还需要创建Thread类并将实现Runnable接口实例对象传入, 然后再调用start方法, 这就是利用Runnable接口实现线程启动的方法。

***不管是继承Thread类还是实现Runnable接口, 想要启动一个新线程最终都是使用Thread类的start方法***

#### 线程暂停
我们可以通过Thread类中的sleep方法让线程进行休眠, sleep方法是一个静态方法。sleep方法方法的调用放在了try...catch中, 这是因为sleep方法可能会抛出
InterruptedException异常。InterruptedException异常能够取消线程的处理(后面会详解)

sleep实际生产中频率不会使用太多, 毕竟没有任何人希望等待很长时间, 但也有一些场景可以使用, 比如游戏结束给定一个时间不继续就退出程序。
```java
public class Main4 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("main");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
```

#### 线程互斥
多线程程序每个线程都是独立自由运行的, 所以它们有时候会同时操作同一个实例。这在某些情况下会引发非常严重的问题。例如最常见的例子
就是银行取钱。

假设我们有如下一段取钱伪代码

```text
if (可用余额大于等于取款金额) {
    从可用余额扣除取出金额
}
```
首先会判断可用余额, 确认是否允许取款。如果允许, 则从可用余额中减去取出金额, 这样可用余额不会出现负数的情况(当然这是针对单线程程序来说)。
但是, 如果存在两个及以上的线程同时执行这段代码, 那么可用余额则有可能成为负数。

假设可用余额为100元, 取出金额为100元, 现在有线程A和线程B同时执行

![两个线程同时执行](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/%E4%B8%A4%E4%B8%AA%E7%BA%BF%E7%A8%8B%E5%90%8C%E6%97%B6%E6%89%A7%E8%A1%8C.png?raw=true)

线程A和线程B同时执行, 有时线程B的处理可能会插在线程A的 "可用余额确认" 和 "从可用余额上减去取款金额" 这两个处理之间。

这种线程A和线程B之间相互竞争而引起的与预期相反的情况称为***数据竞争(data race)或叫竞态条件(race condition)***

此时需要一种机制来解决这种情况, 当一个线程在执行时另外一个线程无法操作。而Java中的synchronized可以实现线程之间的互斥。

##### synchronized方法
我们只需要在方法前面加上synchronized关键字, 那么这个方法就只能由一个线程来运行。只能由一个线程运行是每次只能由一个线程运行的意思,
并不是说仅能让某一特定线程运行。

***这种方法被称为synchronized方法, 有时也称之为同步方法***

```java
public class Bank {
    private int amount;
    private String name;
    
    public String getName() {
        return name;
    }

    // 存款, 添加synchronized关键字, 形成线程之间的互斥 
    public synchronized void deposit(int m) {
        amount += m;
    }

    // 取款, 添加synchronized关键字, 线程进行互斥
    public synchronized boolean withdraw(int m) {
        if (amount >= m) {
            amount -= m;
            return true;
        } else {
            return false;
        }
    }
}

public class BankThread extends Thread {
    @Override
    public void run() {
        bank.deposit(m);
        boolean trak = bank.withdraw(m);
        int amount = bank.getAmount();
    }
}

public class Main6 {
    public static void main(String[] args) {
        Bank bank = new Bank(0, "zs");

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 10; i++) {
            BankThread thread = new BankThread(bank, (i <= 0 ? (i + 1) : i) * 5);
            threads[i] = thread;
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
```

上面例子中Bank类deposit(存款)和withdraw(取款)两个方法都是synchronized(同步)方法。

如果已经有一个线程正在执行Bank实例中的deposit或者withdraw方法, 那么其它线程就无法执行Bank实例中的deposit和withdraw
方法, 需要排队等待。

Bank类还要一个getName方法, 这个方法并不是synchronized方法。所以无论其它线程是否正在运行deposit或者withdraw方法, 都可以随时
运行getName方法。

一个实例中的synchronized(同步)方法, 只能由一个线程运行, 而非synchronized方法可以由任意数量的线程去执行。

![多个线程运行非synchronized方法getName](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/%E5%A4%9A%E4%B8%AA%E7%BA%BF%E7%A8%8B%E8%BF%90%E8%A1%8C%E9%9D%9Esynchronized%E6%96%B9%E6%B3%95getName.png?raw=true)

上图展示了多个线程同时执行非synchronized方法, 我们在synchronized方法左侧放了一个代表 ***"锁"*** 的长方形来表示。
当一个线程获取到锁后, 长方形就像筑起来的墙一样, 防止其它线程进入。

下图展示一个线程在执行deposit方法情况。由于该线程获取到了锁, 所以其它线程无法执行该实例中的synchronized方法。

![synchronized方法每次只能由一个线程执行](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/synchronized%E6%96%B9%E6%B3%95%E6%AF%8F%E6%AC%A1%E5%8F%AA%E8%83%BD%E7%94%B1%E4%B8%80%E4%B8%AA%E7%BA%BF%E7%A8%8B%E6%89%A7%E8%A1%8C.png?raw=true)

当一个线程获取到锁后, 我们就将长方形置红, 这表示该锁已经被某个线程获取。但是非synchronized方法
则完全不受影响。不管线程是否已经获取到锁, 都可以自由进入非synchronized方法。

当某个线程结束了synchronized方法, 便会释放锁。可以看到下图中长方形的锁变为白色, 表示锁已经被释放。

![线程执行完synchronized方法释放锁](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/%E7%BA%BF%E7%A8%8B%E6%89%A7%E8%A1%8C%E5%AE%8Csynchronized%E6%96%B9%E6%B3%95%E9%87%8A%E6%94%BE%E9%94%81.png?raw=true)

当锁被释放后, 其它线程便会获取, 但是获取锁的线程只会有一个, 其余的线程只能再次等待, 直到下次线程释放锁。

![获取到锁的线程执行synchronized方法](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/%E8%8E%B7%E5%8F%96%E5%88%B0%E9%94%81%E7%9A%84%E7%BA%BF%E7%A8%8B%E6%89%A7%E8%A1%8Csynchronized%E6%96%B9%E6%B3%95.png?raw=true)

这里有个知识点需要注意: 每个实例对象拥有一个独立的锁, 并不是说某一个实例中的synchronized方法正在执行,
其它实例中的synchronized方法就不可以执行了。

我们可以创建两个银行实例对象
```tetx
Bank bank1 = new Bank()  
Bank bank2 = new Bank()
```
这两个实例中的synchronized方法可以由不同线程执行。

![不同实例不同锁](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/%E4%B8%8D%E5%90%8C%E5%AE%9E%E4%BE%8B%E4%B8%8D%E5%90%8C%E9%94%81.png?raw=true)

##### synchronized代码块

如果我们只想让方法中的某一部分由一个线程执行, 而非整个方法。就可以使用synchronized代码块, 使用格式如下
```text
synchronized( 表达式 ) {
  ...
}
```
其中的表达式为锁的实例。也就是我们说的对象实例(Bank对象实例)。synchronized代码块用于精确控制互斥处理的执行范围。


***有一点需要注意的是: synchronized静态方法, 使用的不是对象实例, 而是类对象。***

当多个线程执行静态synchronized方法或者synchronized代码块程序, 永远只能有一个线程执行。因为锁的机制不同, 静态方法使用类对象, 非静态方法使用对象实例。

```text
public static void test() {
    synchronized(MyThread.clsss) {
        // ...
    }
}
```

***静态synchronized方法只能有一个线程执行, 非静态synchronized方法可以多个线程去执行, 但必须是不同对象实例***


#### 线程协作
上面我们已经了解到当一个线程执行synchronized方法时, 其它线程就无法再运行这个方法了。这是一个非常简单的互斥处理。
但是, 我们想更加精细化的处理, 而不是单纯的等待其它线程运行结束, 例如下面这种控制:

1. 如果一个队列为空则写入数据; 如果非空则一直等待直到为空
2. 队列为空后, "通知"正在等待的线程


主要根据"队列是否为空"这个条件来控制线程, Java提供了相关的API让线程进入等待状态以及唤醒等待的线程。

- wait: 线程进入等待状态
- notify: 唤醒一个线程
- notifyAll: 唤醒所有等待的线程


##### 线程休息室
现在有两支篮球队(A队和B队)正在打比赛, 由于A队球员犯规导致B队球员受伤, 此时受伤球员就需要场外休息, 换另外一名替补球员上场。
但是呢, 经过20分钟的休息, 受伤的球员有所好转, 此时教练将替补球员换下又换成了该名球员。

随着比分的拉大, 主力军全部被教练叫停进行休息, 让替补球员跟上。
期间, 替补球员M体力不支主动喊停, 教练随即叫上一名新的球员顶上。

我们可以将未上场的球员看成进入等待状态。 当有球员不行时, 会随机(或者指定)某一个球员上场, 可以看成唤醒。
最后的替补队员代替主力军战斗可以理解为唤醒所有等待的线程

##### wait方法
***wait方法会让线程进入等待队列中***  
假设, 我们执行下面这条语句
```text
obj.wait()
```
那么, 当前正在执行的线程便会暂停运行, 并进入实例obj的等待队列中。***这叫做"线程正在obj上wait"***  

如果我们执行下面两条语句
1) wait()
2) this.wait()

两条语句是等价的, 执行wait方法的线程将会进入this的等待队列中, 这时候可以说"线程正在this上wait"

需要注意的是想要执行wait方法, 线程必须持有锁(这是规定), 如果线程进入等待队列, 便会释放持有锁。

![wait方法](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/wait%E6%96%B9%E6%B3%95.jpg?raw=true)

##### notify方法
***notify方法会将等待队列中的一个线程唤醒***  
假设, 我们执行下面的语句
```text
obj.notify()
```
那么obj等待队列中的一个线程将会被唤醒, 然后会退出等待队列

![notify方法](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/notify%E6%96%B9%E6%B3%95.jpg?raw=true)

同wait方法一样, 想要执行notify方法必须先持有锁, 被notify唤醒的线程并不会立即工作, 因为执行notify方法的线程还持有锁, 所以其它线程无法获取锁实例。

当等待队列中存在多个线程时, notify会如何选择唤醒某个线程, 有可能是随机又或者是wait最长时间的线程又或者是其它策略, 这要取决于Java运行环境。
所以在编写程序时, 最好不要有依赖特定的线程, 谁也不知道线程一定会被唤醒。

##### notifyAll方法
***notifyAll方法会将等待队列中的所有线程都唤醒***  
```text
obj.notifyAll()
```
执行上面的语句后, obj等待队列中的全部线程都会被唤醒。

notify和notifyAll唯一的区别就是, notify只能唤醒一个线程, notifyAll能唤醒全部线程。

![notifyAll方法](https://github.com/basebase/java-base/blob/master/src/main/java/cn/xmy/base/ch01/notifyAll%E6%96%B9%E6%B3%95.jpg?raw=true)

同wait和notify方法一样, notifyAll也需要获取锁才能调用。

同样, 被唤醒的全部线程依旧是无法立即工作的, 它们都需要获取锁, 而当前锁实例是被执行notifyAll线程所持有的, 只有等当前线程释放锁或者调用wait方法
其它被唤醒的线程去抢被释放的锁才有机会再次运行。

***如果为持有锁线程调用wait、notify、notifyAll方法, 则会抛出异常。***  



##### wait、notify、notifyAll都是Object方法
wait、notify、notifyAll都是Object方法, 而不是Thread类固有方法。  
我们先来回顾一下wait、notify、notifyAll操作:
* obj.wait(): 将当前线程放入obj的等待队列中
* obj.notify(): 唤醒obj等待队列中的一个线程
* obj.notifyAll(): 唤醒obj等待队列中的全部线程

wait、notify、notifyAll这三个方法与其说是针对线程的操作, 倒不如说是针对实例的等待队列操作。由于所有对象实例都有等待队列, 所以也可就称为Object方法。

虽然上面三个方法不是Thread类固有方法, 但由于Object类是所有类的父类, 也可以说上述三个方法是Thread类方法。


