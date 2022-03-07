package ink.bluecloud.wetalk.UI.MainVIew.Node

import ink.bluecloud.wetalk.UI.MainStyle
import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.shape.Circle
import tornadofx.*

class Member(name: String):HBox(5.0) {
    init {
        imageview("tx.jpg") {
            clip = Circle(10.0,10.0,10.0)
            fitHeight = 20.0
            fitWidth = 20.0
        }

        label(name)

        addClass(MainStyle.memberBox)
        alignment = Pos.CENTER_LEFT
    }
}