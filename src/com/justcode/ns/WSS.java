package com.justcode.ns;

import com.justcode.ns.handler.WebSocketServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * WebSocket服务
 * @author justcode
 *
 */
public class WSS implements Runnable {

	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	int port = 8081;
	@Override
	public void run() {
		try {
			ServerBootstrap a = new ServerBootstrap();
			a.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new WebSocketChannelHandler());
			Channel ch = a.bind(port).sync().channel();
			System.out.println("Web socket server started at port " + port);
			System.out.println("Open you browser and navigate to http://localhost:" + port + "/");
			ch.closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	} 
}

class WebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch){
		try {
			ChannelPipeline pipeline = ch.pipeline();
			//将请求和应答消息编码或者解码为HTTP消息
			pipeline.addLast("http-codec",new HttpServerCodec());
			//将HTTP消息的多个部分组合成一条完整的HTTP消息
			pipeline.addLast("aggregator",new HttpObjectAggregator(65535));
			//来向客户端发送HTML文件，主要用户支持浏览器和服务端进行“WebSocket通信
			ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
			pipeline.addLast("handler",new WebSocketServerHandler());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
