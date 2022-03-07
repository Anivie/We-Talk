package ink.bluecloud.wetalk.Data.Netty.Codec

import com.alibaba.fastjson.JSONObject
import ink.bluecloud.wetalk.Data.EventResourcesPool
import ink.bluecloud.wetalk.UI.MainVIew.MainView
import ink.bluecloud.wetalk.UI.MainVIew.Node.Member
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tornadofx.find

class MemberCodec: ChannelInboundHandlerAdapter() {
    private val ePool = find<EventResourcesPool>()
    private val view = find<MainView>()


    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        println(msg)
        CoroutineScope(ePool.dispatcher).launch {
            if ((msg as JSONObject).containsKey("nicknames")) {
                withContext(Dispatchers.JavaFx) {
                    msg.getJSONArray("nicknames").run {
                        view.memberBox!!.children.clear()
                        forEach {
                            view.memberBox!!.children.add(Member(it as String))
                        }
                    }
                }
            } else {
                super.channelRead(ctx, msg)
            }
        }
    }
}