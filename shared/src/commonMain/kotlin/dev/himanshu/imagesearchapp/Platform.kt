package dev.himanshu.imagesearchapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform