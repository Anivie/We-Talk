package ink.bluecloud.wetalk

import ink.bluecloud.wetalk.UI.MainStyle
import ink.bluecloud.wetalk.UI.MainVIew.MainView
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import tornadofx.*

class MainApp: App(MainView::class,MainStyle::class) {
    init {
        reloadStylesheetsOnFocus()
//        reloadViewsOnFocus()
    }

    override fun start(stage: Stage) {
        super.start(stage.apply {
            initStyle(StageStyle.TRANSPARENT)
        })
    }

    override fun createPrimaryScene(view: UIComponent): Scene {
        return super.createPrimaryScene(view).apply {
            fill = Color.TRANSPARENT
            stylesheets.add("font/font.css")
        }
    }
}