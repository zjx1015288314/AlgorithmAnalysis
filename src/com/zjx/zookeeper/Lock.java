package com.zjx.zookeeper;

public interface Lock {
    boolean lock() throws Exception;

    boolean unlock();
}
