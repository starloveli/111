package com.itstack.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel e) throws Exception {
		//HTTP协议的请求解码器和响应编码器即HttpServerCodec，
		//它会将HTTP客户端请求转成HttpRequest对象，将HttpResponse对象编码成HTTP响应发送给客户端。
		e.pipeline().addLast("http-codec",new HttpServerCodec());
		// 聚合器，把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse 
		//65535应该是服务器端的最大端口数
		e.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));
		// 块写入处理器  ,目的是支持异步大文件传输
		e.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
		// 自定义服务端处理器  
		/*Handler主要用于异步消息的处理：当发出一个消息之后，首先进入一个消息队列，发送消息的函数即刻返回，
		而另外一个部分在消息队列中逐一将消息取出，然后对消息进行处理，也就是发送消息和接收消息不是同步的处理。
		这种机制通常用来处理相对耗时比较长的操作。*/
		e.pipeline().addLast("handler",new MyWebSocketServerHandler());
	}
} 