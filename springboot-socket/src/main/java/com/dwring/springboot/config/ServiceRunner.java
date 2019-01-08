package com.dwring.springboot.config;


import org.apache.logging.log4j.core.config.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.log4j.Log4j2;

@Service
@Order(1)
@Log4j2
public class ServiceRunner implements CommandLineRunner {

	@Value("${netty.port}")
	private int port;

	@Override
	public void run(String... arg0) throws Exception {
		start();
	}

	/**
	 * 启动服务
	 * @throws Exception
	 */
	public void start() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap sb = new ServerBootstrap();
			sb.group(group)// 绑定线程池
					.channel(NioServerSocketChannel.class) // 指定使用的channel
					.localAddress(this.port)// 绑定监听端口
					.childHandler(new ChannelInitializer<Channel>() {
						@Override
						protected void initChannel(Channel channel) throws Exception {
							channel.pipeline().addLast(new SocketHandler());
						}// 绑定客户端连接时候触发操作

					});
			// 服务器异步创建绑定
			ChannelFuture cf = sb.bind().sync();
			log.info("Started, listening on {}", cf.channel().localAddress());
			// 关闭服务器通道
			cf.channel().closeFuture().sync();
		} finally {
			// 释放线程池资源
			group.shutdownGracefully().sync();
		}
	}

}
