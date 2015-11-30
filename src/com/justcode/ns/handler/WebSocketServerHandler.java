package com.justcode.ns.handler;

import static io.netty.handler.codec.http.HttpHeaderUtil.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaderUtil.setContentLength;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import java.util.List;

import com.justcode.jdbc.Person;
import com.justcode.jdbc.PersonDAO;
import com.justcode.ns.util.QueueUtil;

/**
 * websocket服务
 * @author justcode
 *
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>  {
	
	private WebSocketServerHandshaker handshaker;
	private PersonDAO personDAO=new PersonDAO();

	@Override
	public void messageReceived(ChannelHandlerContext ctx, Object msg) {
		System.out.println("messageReceived");
		try {
			// 传统的HTTP接入
			if (msg instanceof FullHttpRequest) {
				handleHttpRequest(ctx, (FullHttpRequest) msg);
			}
			// WebSocket接入
			else if (msg instanceof WebSocketFrame) {
				handleWebSocketFrame(ctx, (WebSocketFrame) msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		System.out.println("channelReadComplete");
		try {
			ctx.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req)  {
		System.out.println("handleHttpRequest");
		try {
			// 如果HTTP解码失败，返回HHTP异常
			if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
				sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,BAD_REQUEST));
				return;
			}

			// 构造握手响应返回，本机测试
			WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/gift", null, false);
			handshaker = wsFactory.newHandshaker(req);
			if (handshaker == null) {
				WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
			} else {
				handshaker.handshake(ctx.channel(), req);
				String str = (String) req.headers().get("Sec-WebSocket-Key");
				//握手成功才建立存储
				QueueUtil.getInstance().addSocket(ctx,str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx,WebSocketFrame frame) {
		System.out.println("handleWebSocketFrame");
		try {
			// 判断是否是关闭链路的指令
			if (frame instanceof CloseWebSocketFrame) {
				System.out.println("CloseWebSocketFrame");
				handshaker.close(ctx.channel(),(CloseWebSocketFrame) frame.retain());
				return;
			}
			// 判断是否是Ping消息
			if (frame instanceof PingWebSocketFrame) {
				System.out.println("PingWebSocketFrame");
				ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
				return;
			}
			// 本例程仅支持文本消息，不支持二进制消息
			if (!(frame instanceof TextWebSocketFrame)) {
				throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
			}
			// 返回应答消息
			String requestSTR = ((TextWebSocketFrame) frame).text();
			String[] str=requestSTR.split(",");
			for(int i=0;i<str.length;i++){
				System.out.println("请求字符="+str[i]);
			}
			//System.out.println("=="+str);
//			QueueUtil.getInstance().broadMsg(request);
			List<Person> list=personDAO.personList();
			QueueUtil.getInstance().personMsg(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx,FullHttpRequest req, FullHttpResponse res) {
		System.out.println("sendHttpResponse");
		try {
			// 返回应答给客户端
			if (res.status().code() != 200) {
				ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(),CharsetUtil.UTF_8);
				res.content().writeBytes(buf);
				buf.release();
				setContentLength(res, res.content().readableBytes());
			}

			// 如果是非Keep-Alive，关闭连接
			ChannelFuture f = ctx.channel().writeAndFlush(res);
			if (!isKeepAlive(req) || res.status().code() != 200) {
				f.addListener(ChannelFutureListener.CLOSE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
	//客户端断开连接会调用，需要队列中移除通道连接
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handlerRemoved");
		QueueUtil.getInstance().removeSocket(ctx);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		System.out.println("exceptionCaught");
		QueueUtil.getInstance().removeSocket(ctx);
		ctx.close();
	}
}
