package me.examplewebmvc.exception

class JsonUtilException (
    override val cause: Throwable? = null
) : RuntimeException(cause)