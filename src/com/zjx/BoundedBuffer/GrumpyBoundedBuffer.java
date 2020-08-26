package com.zjx.BoundedBuffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer {

    //该实现并不好,“缓存已满(空)”不是游街缓存的一个异常条件,这使得调用者管理状态依赖性,且调用者必须做好捕获异常的准备,每次
    //缓存操作时都要重试:
    //while(true){
    //  try{
    //      V item = buffer.take();
    //  }catch(BufferEmptyException){
    //
    //  }
    // }
    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) {
        if (!isFull())
            throw new BufferOverflowException();
        doPut(v);
    }

    public synchronized V take() {
        V v;

        if (isEmpty())
            throw new BufferUnderflowException();

        v = (V) doTake();
        return v;
    }

}
