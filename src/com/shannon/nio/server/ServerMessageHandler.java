package com.shannon.nio.server;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.shannon.nio.AbstractMessageHandler;
import com.shannon.nio.bean.EchoRequest;

/**
 * Created by Shannon,chen on 16/2/29.
 *
 * 服务端处理
 */
public class ServerMessageHandler extends AbstractMessageHandler {
	private final static Logger LOGGER = LogManager.getLogger(ServerMessageHandler.class.getName());

    @Override
    public void accept(SelectionKey selectionKey) throws Exception {
        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
        Selector selector = selectionKey.selector();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(Boolean.FALSE);
        sc.register(selector, SelectionKey.OP_READ);
    }

    @Override
    public void read(SelectionKey selectionKey) throws Exception {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBuffer);
        if (readBytes > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes, "UTF-8");
            LOGGER.info("The echo server receive request :" + body);
            doWrite(sc, body);
        } else if (readBytes < 0) {
            selectionKey.cancel();
            sc.close();
        } else {
            // do nothing
        }
    }

    @Override
    public void connect(SelectionKey selectionKey) throws Exception {
        // do nothing

    }

    private void doWrite(SocketChannel sc, String response) throws Exception {
        EchoRequest echoRequest = JSON.parseObject(response, EchoRequest.class);

        int responseValue = echoRequest.getValue() * 100;
        echoRequest.setDesc("echo server response");
        echoRequest.setValue(responseValue);

        byte[] responseBytes = JSON.toJSONString(echoRequest).getBytes();
        ByteBuffer writerBuffer = ByteBuffer.allocate(responseBytes.length);
        writerBuffer.put(responseBytes);
        writerBuffer.flip();
        sc.write(writerBuffer);

    }
}
