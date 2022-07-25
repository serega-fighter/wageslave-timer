package com.serega.timer

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*
import kotlin.time.Duration.Companion.hours


class PrimaryView : Initializable {

    @FXML
    private lateinit var primaryPane: HBox
    @FXML
    private lateinit var button: Button
    @FXML
    private lateinit var timerTextField: TimerTextArea

    override fun initialize(url: URL, rb: ResourceBundle?) {
        primaryPane.setOnMousePressed { pressEvent ->
            primaryPane.setOnMouseDragged { dragEvent ->
                UIApplication.stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX())
                UIApplication.stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY())
            }
        }

        button.setOnAction {
            timerTextField.pauseOrResume()
        }
    }

    private fun increaseWindow() {
        primaryPane.prefHeight = primaryPane.prefHeight + 10.0
        primaryPane.prefWidth = primaryPane.prefWidth + 30.0
    }
}