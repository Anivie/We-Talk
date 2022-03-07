package ink.bluecloud.wetalk.UI.MainVIew.Node

import javafx.scene.paint.Color
import tornadofx.*

class ItemStyle:Stylesheet() {
    companion object{
        val otherLabel by cssclass()
        val meLabel by cssclass()

        val otherBox by cssclass()
        val meBox by cssclass()
    }

    init {
        otherLabel {
            textFill = Color.BLACK
        }

        meLabel {
            textFill = Color.WHITE
        }

        meBox {
            backgroundColor += c(18,183,245)
            backgroundRadius += box(10.px)
        }

        otherBox {
            backgroundColor += c(229,229,229)
            backgroundRadius += box(10.px)
        }
    }
}