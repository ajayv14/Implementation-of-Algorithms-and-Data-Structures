
 Chapter 1,2,3 - Thread demo

Chapter 4 - ThreadPool - Executor Service

A Thread Pool is a collection of pre-initialized, reusable threads that are available to perform tasks.

The Executor Service in Java provides a higher-level replacement for working with threads directly:


Chapter 5 : Countdown Latch

A CountDownLatch is a synchronization aid in Java that allows one or more threads to wait until a set of operations being performed in other threads completes. 

It works like a countdown timer:

You initialize it with a count (Say 3)

Each thread calls countDown() when it completes its task

Threads that call await() will block until the count reaches zero
