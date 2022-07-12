package com.serega.timer

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import java.net.URL
import java.util.*
import kotlin.time.Duration.Companion.hours


class PrimaryView : Initializable {

    @FXML
    private lateinit var primaryPane: VBox
    @FXML
    private lateinit var timerTextField: TimerTextArea

    override fun initialize(url: URL, rb: ResourceBundle?) {
        primaryPane.setOnMousePressed { pressEvent ->
            primaryPane.setOnMouseDragged { dragEvent ->
                UIApplication.stage.setX(dragEvent.getScreenX() - pressEvent.getSceneX())
                UIApplication.stage.setY(dragEvent.getScreenY() - pressEvent.getSceneY())
            }
        }

        primaryPane.setOnKeyPressed { pressEvent ->
            when (pressEvent.code) {
                KeyCode.SPACE -> increaseWindow()
                KeyCode.EQUALS -> increaseWindow()
                KeyCode.MINUS -> increaseWindow()
            }
        }

        timerTextField.duration = 6.hours
    }

    private fun increaseWindow() {
        primaryPane.prefHeight = primaryPane.prefHeight + 10.0
        primaryPane.prefWidth = primaryPane.prefWidth + 30.0
    }
}