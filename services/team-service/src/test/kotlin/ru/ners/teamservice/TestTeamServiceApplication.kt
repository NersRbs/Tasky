package ru.ners.teamservice

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<TeamServiceApplication>().with(TestcontainersConfiguration::class).run(*args)
}
