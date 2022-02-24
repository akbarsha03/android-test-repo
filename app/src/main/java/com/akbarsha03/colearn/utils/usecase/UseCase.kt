package com.akbarsha03.colearn.utils.usecase

import kotlinx.coroutines.CoroutineScope

abstract class UseCase<in P, R> {

    val tag: String = this.javaClass.simpleName

    @Throws(RuntimeException::class)
    abstract fun execute(scope: CoroutineScope, parameters: P): R
}