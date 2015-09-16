package com.simplegame.agent.network.data;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月4日 上午11:23:25
 * 
 */
@Component
public class DataClient {

    private Logger LOG = LogManager.getLogger(getClass());

	private String host = "127.0.0.1";

	private int port = 1222;

	private boolean success = false;

	private Channel channel;
	
	@PostConstruct
	public void start() {
		NioEventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstarp = new Bootstrap();
		bootstarp.group(group)
				 .channel(NioSocketChannel.class)
				 .handler(new DataClientInitializer());
		
		try {
			channel = bootstarp.connect(host, port).sync().channel();
		} catch (InterruptedException e) {
			LOG.error("connect fialed, host: {}, port: {}", host, port, e);

			e.printStackTrace();
		}

		LOG.info("connect success. host: {}, port: {}", host, port);

		this.success = true;
	}

	public void stop() {
		if (success) {
			channel.disconnect();
		}
	}
	
	public void sendMessage(Object msg) {
		channel.writeAndFlush(msg);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
