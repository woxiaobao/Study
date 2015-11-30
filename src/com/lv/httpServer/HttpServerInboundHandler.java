package com.lv.httpServer;

import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS;
import static io.netty.handler.codec.http.HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderNames.LOCATION;
import static io.netty.handler.codec.http.HttpMethod.DELETE;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpMethod.HEAD;
import static io.netty.handler.codec.http.HttpMethod.OPTIONS;
import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpMethod.PUT;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpServerInboundHandler extends
		SimpleChannelInboundHandler<FullHttpRequest> {

	public static Logger LOG = LogManager
			.getLogger(HttpServerInboundHandler.class);
	public BookServer bookServer=new BookServer();
	public LoginServer login=new LoginServer();

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			FullHttpRequest request) throws Exception {
		if (!request.decoderResult().isSuccess()) {
			sendError(ctx, BAD_REQUEST);
			return;
		}
		System.out.println("method=" + request.method());
		String action="";
		HttpMethod method = request.method();
		if (method == GET) {
			// sendError(ctx, METHOD_NOT_ALLOWED);
			// return;
			final String uri = request.uri();
			String text = "";

			int idx = uri.indexOf('?');
			if (idx <= 0 || idx >= uri.length()) {
				sendError(ctx, FORBIDDEN);
				return;
			}
			action = uri.substring(5, idx);
			if (action.equals("book")) {
				System.out.println("welcome to api=" + action);
				bookServer.index();
			}
			
		}
		if (method == POST) {
			final String uri = request.uri();
			action = uri.substring(5, uri.length());
			if (action.equals("book")) {
				System.out.println("welcome to api=" + action);
				bookServer.save();
			}
			if (action.equals("login")) {
				System.out.println("welcome to api=" + action);
				login.index(request);
			}
			
		}
		if (method == PUT) {
			final String uri = request.uri();
			action = uri.substring(5, uri.length());
			
			if (action.equals("login")) {
				System.out.println("welcome to api=" + action);
				login.edit(request);
			}
		}
		if (method == DELETE) {
			final String uri = request.uri();
			action = uri.substring(5, uri.length());
			
			if (action.equals("login")) {
				System.out.println("welcome to api=" + action);
				login.del(request);
			}
		}
		if (method == OPTIONS) {
			final String uri = request.uri();
			action = uri.substring(5, uri.length());
			
			if (action.equals("login")) {
				System.out.println("welcome to api=" + action);
				login.create(request);
			}
		}
		if (method == HEAD) {
			
		}
		
		// String queryString = uri.substring(idx+1);
		// request的URI中拿不到#
		// idx = queryString.indexOf('#');
		// if(idx == 0) {
		// writeResponse(e, "error format");
		// return;
		// }
		// queryString = queryString.substring(0, idx);
		// String[] kvs = queryString.split("&");
		//
		// String title = null;
		// String content = null;
		// for(String kvString : kvs) {
		// String[] kv = kvString.split("=");
		// if(kv.length != 2) continue;
		// if("title".equals(kv[0])) {
		// title = kv[1];
		// } else if("content".equals(kv[0])) {
		// content = kv[1];
		// }
		// }
		// if(title == null || content == null) {
		// sendError(ctx, FORBIDDEN);
		// }
		// JSONObject obj = new JSONObject();
		// obj.put("title", title);
		// obj.put("content", content);
		//
		// LOG.info("HTTPServer received the new message : "+obj.toString());
		// text=obj.toString();
		//
		//
		// sendListing(ctx, text);
		sendListing(ctx, action);
		// sendError(ctx, UNAUTHORIZED);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		if (ctx.channel().isActive()) {
			sendError(ctx, INTERNAL_SERVER_ERROR);
		}
	}

	private static void sendListing(ChannelHandlerContext ctx, String text) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK);
		response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
		response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, OPTIONS, PUT, DELETE, HEAD");
		StringBuilder buf = new StringBuilder();
		buf.append("欢迎来到这里" + text);
		System.out.println(buf.toString());
		ByteBuf buffer = Unpooled.copiedBuffer(buf, CharsetUtil.UTF_8);
		response.content().writeBytes(buffer);
		buffer.release();
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, FOUND);
		response.headers().set(LOCATION, newUri);
		response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, OPTIONS, PUT, DELETE, HEAD");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private static void sendError(ChannelHandlerContext ctx,
			HttpResponseStatus status) {
		FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
				status, Unpooled.copiedBuffer("Failure: " + status.toString()
						+ "\r\n", CharsetUtil.UTF_8));
		LOG.info("=======" + status);
		response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
		response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, OPTIONS, PUT, DELETE, HEAD");
		ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
	}

	private static void setContentTypeHeader(HttpResponse response, File file) {
		MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
		response.headers().set(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		response.headers().set(ACCESS_CONTROL_ALLOW_METHODS,"POST, GET, OPTIONS, PUT, DELETE, HEAD");
		response.headers().set(CONTENT_TYPE,
				mimeTypesMap.getContentType(file.getPath()));
	}

}
