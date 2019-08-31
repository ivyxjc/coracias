@file:JvmName("InternalUtils")

package xyz.ivyxjc.coracias

import org.slf4j.LoggerFactory

internal fun <T> loggerFor(clz: Class<T>) = LoggerFactory.getLogger(clz)

internal fun <T> loggerFor(str: String) = LoggerFactory.getLogger(str)


