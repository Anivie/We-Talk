package ink.bluecloud.wetalk.Data.Netty.Codec

import com.alibaba.fastjson.JSONObject
import ink.bluecloud.wetalk.Data.EventResourcesPool
import ink.bluecloud.wetalk.UI.MainVIew.MainView
import ink.bluecloud.wetalk.UI.MainVIew.Node.Item
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tornadofx.find

class MessageCodec: ChannelInboundHandlerAdapter() {
    private val ePool = find<EventResourcesPool>()
    private val view = find<MainView>()


    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        CoroutineScope(ePool.dispatcher).launch {
            JSONObject.parseObject(msg as String).run {
                if (containsKey("message")) {
                    withContext(Dispatchers.JavaFx) {
                        view.monitorBox!!.children.add(Item(getString("nickname"),getString("message"),getString("nickname") == ePool.userName))
                    }
                } else {
                    super.channelRead(ctx, this)
                }
            }
        }
    }

    override fun channelActive(ctx: ChannelHandlerContext) {
        ctx.channel().writeAndFlush("${JSONObject().apply {
            put("nickname",ePool.userName)
        }.toJSONString()}\r\n")
        super.channelActive(ctx)
    }
}