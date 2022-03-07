package ink.bluecloud.wetalk.UI.MainVIew

import ink.bluecloud.wetalk.Data.Netty.MainClient
import ink.bluecloud.wetalk.UI.MainStyle
import javafx.application.Platform
import javafx.collections.ListChangeListener
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class MainView : View("吹牛逼") {
    val client = find<MainClient>()
    private val controller by inject<MainViewController>()

    var monitorBox: VBox? = null
    var memberBox: VBox? = null

    /**
     * 定义偏移量，用于处理窗口移动
     */
    private var xOffset = 0.0
    private var yOffset = 0.0

    override val root = vbox {
        hbox {
            style {
                backgroundColor += c(10,8,48)
            }

            label("X") {
                addClass(MainStyle.closeButton)
                setOnMouseClicked {
                    Platform.exit()
                }
            }

            onMousePressed = EventHandler { event: MouseEvent ->
                xOffset = event.sceneX
                yOffset = event.sceneY
            }
            onMouseDragged = EventHandler { event: MouseEvent ->
                primaryStage.x = event.screenX - xOffset
                primaryStage.y = event.screenY - yOffset
            }

            alignment = Pos.TOP_RIGHT
            prefHeight = 40.0
        }

        borderpane {
            right = borderpane {
                top = vbox {
                    label("群通知") {
                        style {
                            fontSize = 14.px
                        }
                    }

                    children.addListener(ListChangeListener {
                        if (children.isEmpty()) {
                            children.add(label("暂时没有新的公告"))
                        }
                    })

                    label("暂时没有新的公告")

                    style {
                        borderWidth += CssBox(1.px, 0.px, 1.px, 1.px)
                        borderColor += box(c(235,235,235))
                    }

                    prefHeight = 150.0
                    paddingAll = 10
                }

                center = vbox {
                    label("群成员") {
                        style {
                            fontSize = 18.px
                        }
                    }

                    vbox {
                        memberBox = this
                    }

                    style {
                        borderWidth += CssBox(1.px, 1.px, 1.px, 1.px)
                        borderColor += box(c(235,235,235))
                    }
                }


                style {
                    backgroundColor += Color.WHITE
                }
                setPrefSize(200.0,400.0)
            }

            center = borderpane {
                top = vbox(10.0) {
                    hbox(10.0) {
                        label("聊天") {
                            addClass(MainStyle.topBarLabel)
                        }
                        label("公告") {
                            addClass(MainStyle.topBarLabel)
                        }
                        label("相册") {
                            addClass(MainStyle.topBarLabel)
                        }
                        label("文件") {
                            addClass(MainStyle.topBarLabel)
                        }
                        label("作业") {
                            addClass(MainStyle.topBarLabel)
                        }
                        label("设置") {
                            addClass(MainStyle.topBarLabel)
                        }

                    }
                    line(endX = 35)

                    paddingAll = 10
                }

                center = scrollpane {
                    content = vbox {
                        this@scrollpane.widthProperty().addListener { _, _, newValue ->
                            prefWidth = newValue.toDouble()
                        }

                        style {
                            borderWidth += CssBox(1.px, 1.px, 0.px, 1.px)
                            borderColor += box(c(235,235,235))
                        }
                        hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER
                        paddingAll = 20
                    }.apply {
                        monitorBox = this
                    }

                    (content as VBox).heightProperty().addListener { _, _, _ ->
                        vvalue = 1.0
                    }

                    addClass(MainStyle.myScrollPane)
                }

                bottom = vbox {
                    borderpane {
                        left = hbox(10.0) {
                            //表情
                            label("\uE64A") {
                                addClass(MainStyle.moduleIcon)
                            }
                            label("\uE70D") {
                                addClass(MainStyle.moduleIcon)
                            }
                            label("\uE600") {
                                addClass(MainStyle.moduleIcon)
                            }
                            label("\uEAC5") {
                                addClass(MainStyle.moduleIcon)
                            }
                            label("\uE61A") {
                                addClass(MainStyle.moduleIcon)
                            }
                            label("\uE6B2") {
                                addClass(MainStyle.moduleIcon)
                            }
                        }

                        right = label("\uE74E") {
                            addClass(MainStyle.moduleIcon)
                        }
                        paddingLeft = 10
                        paddingRight = 10
                        paddingTop = 5
                    }

                    val message = textarea {
                        addClass(MainStyle.messageArea)
                        addEventHandler(KeyEvent.KEY_PRESSED) {
                            if (it.code == KeyCode.ENTER) {
                                it.consume()
                                controller.send(this)
                            }
                        }
                        primaryStage.heightProperty().addListener { _, _, newValue ->
                            maxHeight = newValue.toDouble() / 4
                        }
                    }

                    hbox(5.0) {
                        button("关闭(C)") {
                            addClass(MainStyle.moduleButton)
                            action {
                                Platform.exit()
                                primaryStage.close()
                            }
                        }
                        button("发送(S)") {
                            addClass(MainStyle.sendButton)
                            action {
                                controller.send(message)
                            }
                        }

                        paddingAll = 10
                        alignment = Pos.CENTER_RIGHT
                    }

                    style {
                        borderColor += box(c("#EBEBEB"))
                        borderWidth = multi(CssBox(1.px,0.px,0.px,0.px))
                    }
                }

                style {
                    backgroundColor += Color.WHITE

                    setPrefSize(900.0,750.0)
                }
            }
        }

    }

}
