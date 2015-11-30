package com.lv.httpServer;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

public class LoginServer {
	public static Logger LOG = LogManager
			.getLogger(LoginServer.class);
	private static Jedis jedis = new Jedis("127.0.0.1", 6379);
	public List<InterfaceHttpData> postList=null;
	//验证Login
	public void index(FullHttpRequest request){
		System.out.println(" index");
		Long id=jedis.incr("userId");
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        try{
            postList = decoder.getBodyHttpDatas();
            // 读取从客户端传过来的参数
            for (InterfaceHttpData data : postList) {
                String name = data.getName();
                //LOG.info(data.toString());
                
        		//LOG.info(jedis.get("name"));// 执行结果：xinxin
                String value = null;
                if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    attribute.setCharset(CharsetUtil.UTF_8);
                    value = attribute.getValue();
                    jedis.set(name+":"+id, value);// 向key-->name中放入了value-->xinxin
                    
                }
            }
            LOG.info(jedis.get("username:"+id)+","+jedis.get("password:"+id));

        }catch (Exception e){
            e.printStackTrace();
        }
		
	}
	
	public void edit(FullHttpRequest request){
		System.out.println("edit");
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        try{
            List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
            // 读取从客户端传过来的参数
            for (InterfaceHttpData data : postList) {
                String name = data.getName();
                LOG.info(data.toString());
                String value = null;
                if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    attribute.setCharset(CharsetUtil.UTF_8);
                    value = attribute.getValue();
                    LOG.info("name:"+name+",value:"+value);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public void save(){
		System.out.println(" save");
	}
	public void del(FullHttpRequest request){
		System.out.println(" del");
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        try{
            List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
            // 读取从客户端传过来的参数
            for (InterfaceHttpData data : postList) {
                String name = data.getName();
                LOG.info(data.toString());
                String value = null;
                if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    attribute.setCharset(CharsetUtil.UTF_8);
                    value = attribute.getValue();
                    LOG.info("name:"+name+",value:"+value);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
	}
	
	public void create(FullHttpRequest request){
		System.out.println("create");
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), request);
        try{
            List<InterfaceHttpData> postList = decoder.getBodyHttpDatas();
            // 读取从客户端传过来的参数
            for (InterfaceHttpData data : postList) {
                String name = data.getName();
                LOG.info(data.toString());
                String value = null;
                if (InterfaceHttpData.HttpDataType.Attribute == data.getHttpDataType()) {
                    MemoryAttribute attribute = (MemoryAttribute) data;
                    attribute.setCharset(CharsetUtil.UTF_8);
                    value = attribute.getValue();
                    LOG.info("name:"+name+",value:"+value);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
	}

}
