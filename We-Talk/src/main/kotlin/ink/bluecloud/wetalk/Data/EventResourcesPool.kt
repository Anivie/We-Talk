package ink.bluecloud.wetalk.Data

import com.alibaba.fastjson.JSONObject
import javafx.collections.FXCollections
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import tornadofx.Controller
import java.nio.file.Files
import java.nio.file.Paths

class EventResourcesPool: Controller() {
    val message = FXCollections.observableArrayList<Message>()
    lateinit var dispatcher: ExecutorCoroutineDispatcher

    val userName: String
    init {
        JSONObject.parseObject(Files.readString(Paths.get("config.json"))).run {
            userName = getString("UserName")
        }
    }
}

data class Message(
    val message: String,
    val nickname: String,
    val id: String,
    val time: String
)