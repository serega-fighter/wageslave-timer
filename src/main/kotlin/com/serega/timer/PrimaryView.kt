package com.serega.timer

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import java.net.URL
import java.util.*
import kotlin.math.roundToInt


class PrimaryView : Initializable {

    @FXML
    private lateinit var primaryPane: HBox
    @FXML
    private lateinit var button: Button
    @FXML
    private lateinit var timerTextField: TimerTextArea

    override fun initialize(url: URL, rb: ResourceBundle?) {

        primaryPane.style = "-fx-background-radius: 6; -fx-background-insets: 0, 0 1 1 0;"

        timerTextField.stateListener = object : TimerStateListener {
            override fun onNewState(state: TimerState) {
                val color = when(state) {
                    TimerState.RUNNING -> Color.LIGHTGREEN
                    TimerState.PAUSED -> Color.MOCCASIN
                    TimerState.RESET -> Color.LIGHTGRAY
                }

                primaryPane.style += "-fx-background-color: rgb(${(color.red * 255.0).roundToInt()}, ${(color.green * 255.0).roundToInt()}, ${(color.blue * 255.0).roundToInt()});"
            }
        }

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
}