package ru.application.news_app

fun main(){
    fun sayHello(name: String, message: () -> String){
        println("Hello , $name! ${message()}")
    }
    fun sayBye(name: String, message: () -> String){
        println("Hello , $name! ${message()}")
    }

    sayHello("Alice", {"How are you today"})
    sayBye("John"){
        "See you later!"
    }
}