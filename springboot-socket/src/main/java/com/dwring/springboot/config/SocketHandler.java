package com.dwring.springboot.config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SocketHandler extends ChannelInboundHandlerAdapter {

	/**
	 * 建立连接时，发送一条消息
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("连接的客户端地址:" + ctx.channel().remoteAddress());
		super.channelActive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, "GBK");
		log.info("server channelRead...; received:{}", body);
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("server channelReadComplete...");
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
				// 当flush完成后关闭channel;
				.addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error("exception occur: {}", cause.getMessage());
		cause.printStackTrace();
		ctx.close();
	}
}
