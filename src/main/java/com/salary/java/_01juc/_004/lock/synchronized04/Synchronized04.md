>     https://www.kernel.org/doc/ols/2002/ols2002-pages-479-495.pdf
>     https://locklessinc.com/articles/mutex_cv_futex/
>     https://www.jianshu.com/p/d17a6152740c
>     https://github.com/torvalds/linux/blob/master/kernel/futex.c
>     https://lwn.net/Articles/360699/
>
###    futex & mutex
    The futex [PDF] mechanism, introduced in 2.5.7 by Rusty Russell, Hubertus Franke, and Mathew Kirkwood, 
    is a fast, lightweight kernel-assisted locking primitive for user-space applications. It provides for 
    very fast uncontended lock acquisition and release. The futex state is stored in a user-space 
    variable (an unsigned 32-bit integer on all platforms). Atomic operations are used in order to change 
    the state of the futex in the uncontended case without the overhead of a syscall. 
    In the contended cases, the kernel is invoked to put tasks to sleep and wake them up.

    Futexes are the basis of several mutual exclusion constructs commonly used in threaded programming. 
    These include pthread mutexes, condvars, semaphores, rwlocks, and barriers. 
    They have been through a lot of reconstructive and cosmetic surgery over the last several years, 
    and are now more efficient, more functional, and better documented than ever before.
------