package com.shannon.nio.server;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.shannon.nio.AbstractMessageHandler;
import com.shannon.nio.EchoReactor;

/**
 * Created by Shannon,chen on 16/2/26.
 * <p/>
 * 服务端
 */
public class EchoServer {
    private final static Logger LOGGER = LogManager.getLogger(EchoServer.class.getName());

    private ServerSocketChannel serverSocketChannel;
    private EchoReactor reactor;

    public EchoServer(String host, int port, AbstractMessageHandler messageHandler) {

        try {
            reactor = new EchoReactor(messageHandler);

            serverSocketChannel = ServerSocketChannel.open(); // 初始化reactor线程
            serverSocketChannel.socket().setReuseAddress(Boolean.TRUE); // 打开ServerSocketChannel
            serverSocketChannel.configureBlocking(Boolean.FALSE);  // serverSocketChannel

            serverSocketChannel.bind(new InetSocketAddress(host, port)); // 绑定监听地址和端口

            // 向Reactor线程的多路服用器注册OP_ACCEPT事件
            reactor.registerChannel(serverSocketChannel, SelectionKey.OP_ACCEPT, messageHandler);

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("server start up");
    }

    public void stop() {
        reactor.stop();
    }

    public void start() {
        reactor.run();
    }

}
