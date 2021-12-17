### Single Threaded Execution模式

#### Single Threaded Execution模式
现在有一座独木桥, 每次只允许一个人经过, 如果这个人没有走到桥的对面, 则下一个人无法过桥。如果两个人同时过桥, 则桥会塌掉, 过桥的人掉入河里。

所谓Single Threaded Execution模式, 意即***以一个线程执行***。就像独木桥同一时间内只允许一个人通过一样, ***该模式用于设置限制***
以确保同一时间内只有一个线程执行处理。

Single Threaded Execution有时候也被称为***临界区或者临界域***。  
Single Threaded Execution这个名称侧重执行处理的线程(过桥的人), 而临界区或临界域的名称则侧重于执行范围(人过的桥)。


