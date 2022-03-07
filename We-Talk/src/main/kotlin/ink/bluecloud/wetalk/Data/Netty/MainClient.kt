package ink.bluecloud.wetalk.Data.Netty

import ink.bluecloud.wetalk.Data.EventResourcesPool
import ink.bluecloud.wetalk.Data.Netty.Codec.MemberCodec
import ink.bluecloud.wetalk.Data.Netty.Codec.MessageCodec
import io.netty.bootstrap.Bootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LineBasedFrameDecoder
import io.netty.handler.codec.string.StringDecoder
import io.netty.handler.codec.string.StringEncoder
import javafx.stage.Stage
import javafx.stage.WindowEvent
import kotlinx.coroutines.*
import tornadofx.Controller
import java.net.InetSocketAddress

class MainClient:Controller() {
    var clientChannel: Channel? = null
    private val ePool by inject<EventResourcesPool>()

    init {
        val bootstrap = Bootstrap().apply {
            group(NioEventLoopGroup().apply {
                primaryStage.addEventHandler(WindowEvent.WINDOW_HIDING) {
                    shutdownGracefully()
                }
            })
            channel(NioSocketChannel::class.java)
            handler(MyInitializer(ePool))
            ePool.dispatcher = config().group().asCoroutineDispatcher()
        }

        CoroutineScope(Dispatchers.IO).launch {
            getConnection(bootstrap,primaryStage)
        }
    }

    private fun getConnection(bootstrap: Bootstrap, stage: Stage) {
        CoroutineScope(Dispatchers.IO).launch {
            bootstrap.connect(
                InetSocketAddress(
                    "103.145.87.185",
                    32167
                )
/*                InetSocketAddress(
                    "localhost",
                    7070
                )*/
            ).run {
                addListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (it.isSuccess) {
                            clientChannel = channel()
                        } else {
                            println( "连接服务器失败！正在尝试重新连接。。。")
                            delay(10000)
                            getConnection(bootstrap, stage)
                        }
                        if (it.isSuccess) {
                            clientChannel = channel()
                        }
                    }
                }
            }
        }
    }

    private class MyInitializer(private val ePool: EventResourcesPool) : ChannelInitializer<NioSocketChannel>() {
        override fun initChannel(ch: NioSocketChannel) {
            ch.pipeline().addLast(LineBasedFrameDecoder(Int.MAX_VALUE))
            ch.pipeline().addLast(StringDecoder())
            ch.pipeline().addLast(MessageCodec())
            ch.pipeline().addLast(MemberCodec())
            ch.pipeline().addLast(StringEncoder())
        }
    }
}