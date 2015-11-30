package com.justcode.ns;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import com.justcode.ns.handler.TCPServerHandler;

/**
 * TCP服务
 * @author justcode
 *
 */
public class TCPS implements Runnable{
	EventLoopGroup bossGroup = new NioEventLoopGroup();
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	int port = 8090;
	
	@Override
	public void run() {
		try {
			ServerBootstrap a = new ServerBootstrap();
			a.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new TCPChannelHandler());
			Channel ch = a.bind(port).sync().channel();
			System.out.println("TCP server started at port " + port);
			ch.closeFuture().sync();
		}catch(Exception e){
			
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
}

class TCPChannelHandler extends ChannelInitializer<SocketChannel> {
	private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static final TCPServerHandler SERVERHANDLER = new TCPServerHandler();

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(1000, Delimiters.lineDelimiter()));
        // the encoder and decoder are static as these are sharable
        pipeline.addLast("decoder", DECODER);
        pipeline.addLast("encoder", ENCODER);

        // and then business logic.
        pipeline.addLast("handler", SERVERHANDLER);
    }

}