package net.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @Author： xinjingjie
 * @Date：2021/3/23 14:01
 **/
public class MyServerDemo {
    private ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);
    private ByteBuffer writeBuffer = ByteBuffer.allocateDirect(1024);
    private Selector selector;

    public static void main(String[] args) throws IOException {
        MyServerDemo serverDemo = new MyServerDemo();
        serverDemo.init();
        serverDemo.go();
    }

    public void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selector = Selector.open();
        this.selector = selector;
        SocketAddress address = new InetSocketAddress(8080);
//        ServerSocket socketChannel = serverSocketChannel.socket();
//        socketChannel.bind(address);
        serverSocketChannel.bind(address);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void go() throws IOException {
        //selector.select()是阻塞的，必须等到有一个返回
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    System.out.println(socketChannel.getRemoteAddress() + " register");
                    // 新注册channel
                    if (socketChannel == null) {
                        continue;
                    }
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    buffer.put("hi new channel".getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    System.out.print(channel.getRemoteAddress() + ":  ");
                    readBuffer.clear();
                    channel.read(readBuffer);
                    readBuffer.flip();
                    while (readBuffer.remaining() > 0) {
                        String receiveData = StandardCharsets.UTF_8.decode(readBuffer).toString();
                        System.out.println(receiveData);
                        if ("who am i".equals(receiveData)) {
//                            writeBuffer.clear();
//                            writeBuffer.put(("you are " + channel.getRemoteAddress()).getBytes());
//                            writeBuffer.flip();
//                            channel.write(writeBuffer);
                            selectionKey.attach("you are " + channel.getRemoteAddress());
                        }
                    }
                }
                if (selectionKey.isWritable()) {
                    write(selectionKey);
                }
            }
        }
    }

    private void write(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        String message = (String) selectionKey.attachment();
        if (message == null) {
            return;
        }
        selectionKey.attach(null);
        writeBuffer.clear();
        writeBuffer.put(message.getBytes());
        writeBuffer.flip();
        while (writeBuffer.hasRemaining()) {
            socketChannel.write(writeBuffer);
        }
    }
}
