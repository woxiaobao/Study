package com.lv.redis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

public class RedisTest {
	private static Logger LOG = LogManager.getLogger(RedisTest.class);
//	private static Jedis jedis = new Jedis("127.0.0.1", 6379);
	private static Jedis jedis =RedisUtil.getJedis();

	public static void main(String[] args) {
		RedisTest t = new RedisTest();

		 t.testString();
//		t.testMap();
//		t.testList();
//		t.testSet();
//		t.testRedisPool();
//		try {
//			t.testSort();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void setup() {
		// 连接redis服务器，127.0.0.1:6379
		jedis = new Jedis("127.0.0.1", 6379);
		// 权限认证
		jedis.auth("admin");
		LOG.info("come redis haha");
	}

	/**
	 * redis操作String
	 */
	public void testString() {
		// -----添加数据----------
		jedis.set("name", "xinxin");// 向key-->name中放入了value-->xinxin
		LOG.info(jedis.get("name"));// 执行结果：xinxin

		jedis.append("name", " is my lover"); // 拼接
		LOG.info(jedis.get("name"));

		jedis.del("name"); // 删除某个键
		LOG.info(jedis.get("name"));

		// 设置多个键值对
		jedis.mset("name", "liuling", "age", "23", "qq", "476777389");
		jedis.incr("age"); // 进行加1操作
		LOG.info(jedis.get("name") + "-" + jedis.get("age") + "-"
				+ jedis.get("qq"));
	}

	/**
	 * redis操作Map
	 */
	public void testMap() {
		// -----添加数据----------
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "xinxin");
		map.put("age", "22");
		map.put("qq", "123456");
		jedis.hmset("user", map);
		// 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
		// 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
		List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
		LOG.info(rsmap);

		// 删除map中的某个键值
		jedis.hdel("user", "age");
		LOG.info(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
		LOG.info(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
		LOG.info(jedis.exists("user"));// 是否存在key为user的记录 返回true
		LOG.info(jedis.hkeys("user"));// 返回map对象中的所有key
		LOG.info(jedis.hvals("user"));// 返回map对象中的所有value

		Iterator<String> iter = jedis.hkeys("user").iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			LOG.info(key + ":" + jedis.hmget("user", key));
		}
	}

	/**
	 * jedis操作List
	 */
	public void testList() {
		// 开始前，先移除所有的内容
		jedis.del("java framework");
		LOG.info(jedis.lrange("java framework", 0, -1));
		// 先向key java framework中存放三条数据
		jedis.lpush("java framework", "spring");
		jedis.lpush("java framework", "struts");
		jedis.lpush("java framework", "hibernate");
		// 再取出所有数据jedis.lrange是按范围取出，
		// 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
		LOG.info(jedis.lrange("java framework", 0, -1));

		jedis.del("java framework");
		jedis.rpush("java framework", "spring");
		jedis.rpush("java framework", "struts");
		jedis.rpush("java framework", "hibernate");
		LOG.info(jedis.lrange("java framework", 0, -1));
	}

	/**
	 * jedis操作Set
	 */
	public void testSet() {
		// 添加
		jedis.sadd("user", "liuling");
		jedis.sadd("user", "xinxin");
		jedis.sadd("user", "ling");
		jedis.sadd("user", "zhangxinxin");
		jedis.sadd("user", "who");
		// 移除noname
		jedis.srem("user", "who");
		LOG.info(jedis.smembers("user"));// 获取所有加入的value
		LOG.info(jedis.sismember("user", "who"));// 判断 who
													// 是否是user集合的元素
		LOG.info(jedis.srandmember("user"));
		LOG.info(jedis.scard("user"));// 返回集合的元素个数
	}

	/**
	 * redis排序
	 * @throws InterruptedException
	 */
	public void testSort() throws InterruptedException {
		// jedis 排序
		// 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
		jedis.del("a");// 先清除数据，再加入数据进行测试
		jedis.rpush("a", "1");
		jedis.lpush("a", "6");
		jedis.lpush("a", "3");
		jedis.lpush("a", "9");
		LOG.info(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
		LOG.info(jedis.sort("a")); // [1, 3, 6, 9] //输入排序后结果
	}

	public void testRedisPool() {
		RedisUtil.getJedis().set("newname", "中文测试");
		LOG.info(RedisUtil.getJedis().get("newname"));
	}

}
