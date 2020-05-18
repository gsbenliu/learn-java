Java中wait、sleep、join和yield方法的区别
------
###### > public static native void yield();  
> 向调度程序发出的提示，表明当前线程愿意放弃使用当前的处理器资源。调度程序可以忽略这个提示。
> yield是一种启发式的改进线程之间的相对进程的尝试，否则会过度使用CPU。
> 它的使用应该与详细的分析和基准测试相结合，以确保它实际具有预期的效果。
>很少适合使用这种方法。对于调试或测试目的，它可能很有用，因为它可以帮助重现由于竞态条件而产生的错误
- - - 
###### > public static native void sleep(long millis) throws InterruptedException;
> 使当前正在执行的线程在指定的毫秒数内休眠（临时停止执行）；
> 线程在休眠的过程中，不会释放任何已经得到的锁；
- - - 
###### > public final synchronized void join(long millis) throws InterruptedException
> 等待线程死亡的时间最多为{millis}毫秒，如果{millis}设置为0时，将意味着一直等待下去。
> 此实现使用{this.isAlive()}为条件，循环调用{Object.wait()}方法
- - - 
> 1.sleep、yield方法是静态方法；作用的是当前执行的线程;  
> 2.yield方法释放了cpu的执行权，但是依然保留了cpu的执行资格。给个简单的例子：很多人排队上WC，刚好排上yield上了，现在yield说，出让它这次机会，与更急的人一起比赛谁能更快进入到WC中去。这个比赛可能是其他的人，也可能就是yield本身；  
> 3.wait释放CPU资源，同时释放锁；  
> 4.sleep释放CPU资源，但不释放锁；  

