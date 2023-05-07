package com.jeanbernad.classifyvalue.core

interface Mapper<in T, out P> {

    fun map(item: T): P

}