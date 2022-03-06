package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.Netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class NIOServer implements Runnable {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public static void main(String[] args) {
        new Thread(new NIOServer(8765), "NioServer-001").start();
    }

    public NIOServer(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start, port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                selector.select();
                Iterator iterator = selector.selectedKeys().iterator();
                SelectionKey key = null;
                while (iterator.hasNext()) {
                    key = (SelectionKey) iterator.next();
                    iterator.remove();
                    try {
                        handleEvent(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleEvent(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                handleAccept(key);
            }
            if (key.isReadable()) {
                handleRead(key);
            }
        }
    }

    private void handleAccept(SelectionKey key) throws IOException {
        System.out.println("connect accept,time" + System.currentTimeMillis());
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(this.selector, SelectionKey.OP_READ);
    }

    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBuffer);
        if (readBytes > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes).trim();
            System.out.println("server recive body:" + body);
            String response = "currentTime=" + new Date().toString();
            doWrite(sc, response);
        } else if (readBytes < 0) {
            key.cancel();
            sc.close();
        }
    }

    private void doWrite(SocketChannel socketChannel, String response) throws IOException {
        byte[] bytes = response.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if (!writeBuffer.hasRemaining()) {
            System.out.println("response 2 client succeed:" + response);
        }
    }
}