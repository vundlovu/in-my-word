package org.example.inmyword

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform