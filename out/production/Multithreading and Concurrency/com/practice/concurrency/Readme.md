## Order of Reading: 

    1. Thread Creation
    2. Thread Wait using join()
    3. Daemon Threads
    4. Synchronization Problem
    5. Synchronization Solution Using synchronized
    6. Synchronized keyword problem
    7. Locking levels Example
    8. Locking with Custom Objects
    9. Wait and Notify()
    10. Producer Consumer with wait and notify
    11. Reentrant locks
    12. Volatile Example
    13. DeadLock
    14. LiveLock
    15. Atomic Variables
    16. Semaphores and Mutex

## Notes: 


    1. Stack Memory: stores the local variables and method arguments as well as method calls are stored on the stack.
    
    2. Heap Memory: Objects are stored on the heap memory and live as long as it is referred from somewhere in the application.
    
    3. Threads in Java : 
    * Java has two types of threads - 1. Daemon Threads  and  2. Worker Threads
    *
    * On Java Application startup, it creates 1 worker thread called as Main (for executing main)
    * and several other daemon threads for performing garbage collection operations in background.
    *
    * Daemon Threads are low priority threads that run in background and are used to perform I/O or GC operations.
    *
    * Java terminates all the daemon threads once it is done executing all the worker threads.
    *
    * When we create threads in main then these threads are created as a child of main thread.
    
    4. Every Thread has its own stack memory but all threads share the heap memory.
    
    5. Stack memory is fast as compared to heap memory.
    
    6. Only one thread can access the synchronized method at a time.
    
    7. Every Object in java has intrinsic lock (monitor) which takes a lock on the class 
    when it enters in the synchronized methods or block.
    
    8. Because of the intrinsic lock, no two thread can execute the same synchronized method at the same time.
    
    9. Since the object has only one monitor (intrinsic) lock, it becomes a problem when we have two "Independent"
    synchronized methods in a class, Now thread one has to wait for the other Thread to obtain the lock and finish its 
    work even if they are independent of each other.
    
    10. Difference between sleep and wait methods
        * you call wait on the Object while on the other hand you call sleep on the Thread itself
        
        * wait can be interrupter (this is why we need the InterruptedException) while on the other hand sleep can not
        
        * wait (and notify) must happen in a synchronized  block on the monitor object whereas sleep does not
        
        * sleep operation does not release the locks it holds while on the other hand wait releases the lock on the 
        object that wait() is called on