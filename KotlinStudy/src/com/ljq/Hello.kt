package com.ljq


class Hello( var name : String){
    fun greet(){
        println("hello , $name")
    }
}
fun main(args : Array<String>) {

    Hello("lisi").greet();
}
