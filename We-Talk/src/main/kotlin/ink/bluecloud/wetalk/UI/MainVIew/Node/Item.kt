package ink.bluecloud.wetalk.UI.MainVIew.Node

import javafx.geometry.Pos
import javafx.scene.layout.HBox
import javafx.scene.shape.Circle
import tornadofx.*

class Item(name: String,message: String,isMe: Boolean): HBox(5.0) {
    init {
        imageview("tx.jpg") {
            clip = Circle(20.0,20.0,20.0)
            fitHeight = 40.0
            fitWidth = 40.0
        }

        vbox {
            label(if (isMe) {
                "【主人】${name}"
            } else {
                "【仆人】${name}"
            }) {
                textFill = c(136,136,136)
            }

            hbox {
                label(message) {
                    addStylesheet(ItemStyle::class)
                    if (isMe) {
                        addClass(ItemStyle.meLabel)
                    } else {
                        addClass(ItemStyle.otherLabel)
                    }
                }

                addStylesheet(ItemStyle::class)
                if (isMe) {
                    addClass(ItemStyle.meBox)
                } else {
                    addClass(ItemStyle.otherBox)
                }

                isFillWidth = false
                paddingAll = 10
            }

            alignment = if (isMe) {
                Pos.TOP_RIGHT
            } else {
                Pos.TOP_LEFT
            }
        }

        alignment = if (isMe) {
            children.swap(0,1)
            Pos.TOP_RIGHT
        } else {
            Pos.TOP_LEFT
        }
    }
}