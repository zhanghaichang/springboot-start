# Netty Socket Server 服务器端

主要由两部分组成：

* `ServerBootstrap`:服务器启动引导器。负责配置服务器端基本信息，并且完成服务器的启动
* `EchoServerHandler`：回写的业务逻辑处理器

### ServerBootstrap

首先是编写服务端的启动类，代码中相应的注释在写得很详细。主要的步骤如下：

创建一个ServerBootstrap实例

创建一个EventLoopGroup来处理各种事件，如处理链接请求，发送接收数据等。

设置本地监听端口 InetSocketAddress( port)

设置 childHandler 来设置通道初始化类。并且在通道初始化时，加入回写的业务逻辑处理器EchoServerHandler到服务器通道的pipeline中 。childHandler 在通道初始化时，会被执行一次。

所有准备好之后调用ServerBootstrap.bind() 方法绑定 Server

不过需要注意的是，在不使用Spring的环境中，是通过main方法直接启动服务端，因此是直接new一个处理器echoServerHandler 对象。而在和Spring 整合之后，我们需要将 echoServerHandler 处理器交给springBoot去管理。

ServerBootstrap 代码如下：

```java
@Service("EchoServer")
public class EchoServer
{
    // 服务器端口
    @Value("${server.port}")
    private int port;
    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup boss = new NioEventLoopGroup();
    private static EventLoopGroup work = new NioEventLoopGroup();
​
    // 启动引导器
    private static ServerBootstrap b = new ServerBootstrap();
    @Autowired
    private EchoServerHandler echoServerHandler;
​
    public void run()
    {
        try
        {
            b.group(boss, work);
            // 设置nio类型的channel
            b.channel(NioServerSocketChannel.class);
            // 设置监听端口
            b.localAddress(new InetSocketAddress(port));
            // 设置通道初始化
            b.childHandler(new ChannelInitializer<SocketChannel>()
            {
                //有连接到达时会创建一个channel
                protected void initChannel(SocketChannel ch) throws Exception
                {
                    // pipeline管理channel中的Handler
                    // 在channel队列中添加一个handler来处理业务
                    ch.pipeline().addLast("echoServerHandler",echoServerHandler);
                }
            });
            // 配置完成，开始绑定server
            // 通过调用sync同步方法阻塞直到绑定成功
​
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listen on " + f.channel().localAddress());
​
            // 监听服务器关闭事件
            // 应用程序会一直等待，直到channel关闭
            f.channel().closeFuture().sync();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            // 关闭EventLoopGroup，释放掉所有资源包括创建的线程
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
​
    }
}
```

#### 业务逻辑ServerHandler

```java

​@Service("echoServerHandler")
public class EchoServerHandler extends ChannelInboundHandlerAdapter
{
​
    /**
     * 建立连接时，发送一条消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception
    {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }
​
    public void channelRead(ChannelHandlerContext ctx, Object msg)
    {
        try
        {
            System.out.println("server received data :" + msg);
            ctx.write(msg);//写回数据，
​
        } finally
        {
            ReferenceCountUtil.release(msg);
        }
    }
​
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        //flush掉所有写回的数据
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE); //当flush完成后关闭channel
    }
​
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        //捕捉异常信息
        cause.printStackTrace();
        //出现异常时关闭channel
        ctx.close();
    }
}
​
```