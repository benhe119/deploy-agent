package com.simplegame.agent;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.simplegame.agent.network.data.DataClient;
import com.simplegame.protocol.proto.Message.Request;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月4日 上午11:52:53
 *
 */

public class DataClientTest extends BasicTest {

    private DataClient client;
    
    @Before
    public void init() {
        client = ctx.getBean(DataClient.class);
        client.start();
    }
	
    /**
     * 注册 
     * 
     */
	@Test
	public void register() throws InterruptedException {
		//Data
		JSONArray array = new JSONArray();
		array.add("vip1"); //agentId
		
		Request.Builder builder = Request.newBuilder();
		builder.setCommand("10001")
		       .setData(array.toJSONString());
		
		client.sendMessage(builder);
		
		Thread.sleep(30000);
	}
}
