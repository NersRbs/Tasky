package ru.ners.teamservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TeamServiceApplication

fun main(args: Array<String>) {
	runApplication<TeamServiceApplication>(*args)
}
