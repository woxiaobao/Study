package com.lv.httpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HTTPServer {

	public static Logger LOG = LogManager.getLogger(HTTPServer.class);

	public void run(final int port) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast("http-decoder",
									new HttpRequestDecoder());
							ch.pipeline().addLast("http-aggregator",
									new HttpObjectAggregator(65536));
							ch.pipeline().addLast("http-encoder",
									new HttpResponseEncoder());
							ch.pipeline().addLast("http-chunked",
									new ChunkedWriteHandler());
							ch.pipeline().addLast("httpServerHandler",
									new HttpServerInboundHandler());
						}
					});
			ChannelFuture future = b.bind("127.0.0.1", port).sync();
			System.out.println("HTTPServer服务器启动，网址是 : " + "http://127.0.0.1:"
					+ port );
			future.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
//		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
//		BookServer book=(BookServer) context.getBean("bookServer");
//		book.index();
		
		int port = 8084;
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		new HTTPServer().run(port);
	}
}
