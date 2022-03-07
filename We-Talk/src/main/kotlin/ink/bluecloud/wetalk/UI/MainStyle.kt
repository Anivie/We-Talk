package ink.bluecloud.wetalk.UI

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class MainStyle: Stylesheet() {
    companion object{
        val moduleButton by cssclass()
        val sendButton by cssclass()
        val moduleIcon by cssclass()
        val topBarLabel by cssclass()
        val myScrollPane by cssclass()
        val messageArea by cssclass()

        val closeButton by cssclass()

        val memberBox by cssclass()
    }

    init {
        moduleButton {
            and(hover) {
                backgroundColor += c(239,239,240)
            }
            and(pressed) {
                backgroundColor += c(225,226,227)
            }

            fontSize = 15.px
            backgroundColor += Color.WHITE
            borderColor += box(c("#EBEBEB"))
        }

        sendButton {
            and(hover) {
                backgroundColor += c(122,132,191)
            }
            and(pressed) {
                backgroundColor += c(79,87,160)
            }

            fontSize = 15.px
            textFill = Color.WHITE
            backgroundColor += c(59,72,150)
            borderWidth += box(0.px)
        }

        moduleIcon {
            and(hover) {
                backgroundColor += c(241,241,241)
            }
            and(pressed) {
                backgroundColor += c(230,230,230)
            }
            padding = box(0.0.px, 5.0.px)
            fontSize = 25.px
            textFill = c(135,139,153)
        }

        topBarLabel {
            fontSize = 20.px
            fontWeight = FontWeight.THIN
            textFill = c(136,136,136)
        }

        myScrollPane {
            backgroundColor += Color.TRANSPARENT
            child(viewport) {
                backgroundColor += Color.TRANSPARENT
            }

            child(scrollBar) {
                child(thumb) {
                    backgroundColor += c(205,205,205)
                    backgroundInsets += box(0.px)
                    backgroundRadius += box(5.px)

                    and(hover,pressed) {
                        backgroundColor += c(153,153,153)
                    }
                }

                prefWidth = 10.px
                minWidth = 1.px
                backgroundColor += Color.WHITE
            }
        }

        messageArea {
            and(focused) {
                borderColor += box(Color.TRANSPARENT)
                backgroundColor += Color.WHITE
            }

            fontSize = 15.px
            accentColor = c(0,120,215)
        }

        closeButton {
            textFill = Color.WHITE
            fontSize = 15.px

            padding = box(10.px,15.px)

            and(hover) {
                backgroundColor += c(255,84,57)
            }
            and(pressed) {
                backgroundColor += c(224,74,50)
            }
        }

        memberBox {
            fontSize = 15.px
            padding = box(0.px,10.px)
            and(hover) {
                backgroundColor += c(245,245,245)
            }
        }
    }
}