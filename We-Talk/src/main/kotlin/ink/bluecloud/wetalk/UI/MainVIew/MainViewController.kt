package ink.bluecloud.wetalk.UI.MainVIew

import com.alibaba.fastjson.JSONObject
import ink.bluecloud.wetalk.Data.EventResourcesPool
import ink.bluecloud.wetalk.UI.MainVIew.Node.Item
import javafx.application.Platform
import javafx.scene.control.TextArea
import tornadofx.Controller

class MainViewController: Controller() {
    private val view by inject<MainView>()
    private val ePool = tornadofx.find<EventResourcesPool>()

    fun send(text: TextArea){
        view.client.clientChannel!!.writeAndFlush("${JSONObject().apply {
/*            put("message",text.text.let {
                "$it\n\n————by狂拽酷炫屌炸天无敌拉风还可爱的吹牛逼客户端"
            })*/
            put("message",text.text)
        }.toJSONString()}\r\n").addListener {
            if (it.isSuccess) {
                Platform.runLater {
                    view.monitorBox?.children?.add(Item(ePool.userName,text.text,true))
                    text.text = ""
                }
            }
        }
    }

    private data class Send(
        val message: String,
        val name: String
    )
}

