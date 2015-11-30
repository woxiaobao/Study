package com.justcode.ns.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.justcode.ns.util.QueueUtil;

@Sharable
public class TCPServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	ctx.writeAndFlush("getid\n");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
    	if(msg.length() == 6){
    		QueueUtil.getInstance().addSocket(ctx,msg);
    	}else if("getsize".equals(msg)){
    		QueueUtil.getInstance().broadMsg("当前队列大小："+QueueUtil.getInstance().getSize());
    	}else{
    		QueueUtil.getInstance().broadMsg(msg);
    	}
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    
    //客户端断开连接会调用，需要队列中移除通道连接
	 @Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		 QueueUtil.getInstance().removeSocket(ctx);
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	QueueUtil.getInstance().removeSocket(ctx);
        ctx.close();
    }
    
}