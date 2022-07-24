package com.example.robotcontroller.Brain

import com.example.robotcontroller.model.Robot

class CommandsImpl(val robot: Robot): Commands {

    override fun moveForward() {

        robot.y++
    }

    override fun moveBackwards() {

        robot.y--
    }

    override fun moveRight() {

        robot.x++
    }

    override fun moveLeft() {

        robot.x--
    }
}