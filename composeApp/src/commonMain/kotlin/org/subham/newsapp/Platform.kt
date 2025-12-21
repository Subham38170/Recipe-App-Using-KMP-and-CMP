package org.subham.newsapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform