package com.akbarsha03.colearn.network.interceptors

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset

enum class LogLevel {
    NONE,
    CURL
}

class CurlInterceptor constructor(
    private val tag: String = "CURL_TAG",
    private var logLevel: LogLevel = LogLevel.NONE
) : Interceptor {
    private lateinit var curlCommandBuilder: StringBuilder
    private val charset = Charset.forName("UTF-8")

    init {
        Log.d(tag, "Curl initialised: Install cURL from https://curl.se/download.html")
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (logLevel == LogLevel.CURL) {
            Log.d(tag, "Curling request")
            try {
                printCurls(request)
            } catch (e: Exception) {
                Log.d(tag, "intercept: ${e.message}", e)
            } finally {
                return chain.proceed(request)
            }
        } else {
            Log.d(tag, "Curling skipped")
        }
        return chain.proceed(request)
    }

    @SuppressLint("DefaultLocale")
    private fun printCurls(request: Request) {
        curlCommandBuilder = StringBuilder("")
        // add cURL command
        curlCommandBuilder.append("cURL ")
        curlCommandBuilder.append("-X ")
        // add method
        curlCommandBuilder.append(request.method.toUpperCase() + " ")
        // adding headers
        for (headerName in request.headers.names()) {
            addHeader(headerName, request.headers[headerName])
        }

        // adding request body
        val requestBody = request.body
        if (request.body != null) {
            val buffer = Buffer()
            requestBody!!.writeTo(buffer)
            var charset = charset
            val contentType = requestBody.contentType()
            if (contentType != null) {
                addHeader("Content-Type", request.body!!.contentType().toString())
                charset = contentType.charset(this.charset)
                curlCommandBuilder.append(" -d '" + buffer.readString(charset) + "'")
            }
        }

        // add request URL
        curlCommandBuilder.append(" '" + request.url.toString() + "'")
        curlCommandBuilder.append(" -L")
        printCurl(tag, request.url.toString(), curlCommandBuilder.toString())
    }

    private fun addHeader(headerName: String, headerValue: String?) {
        curlCommandBuilder.append("-H '$headerName: $headerValue' ")
    }

    private val divider = "────────────────────────────────────────────"

    private fun printCurl(tag: String?, url: String, msg: String?) {
        val logMsg = StringBuilder("\n")
        logMsg.apply {
            append("\n")
            append("URL: $url")
            append("\n")
            append(divider)
            append("\n")
            append(msg)
            append(" ")
            append(" \n")
            append(divider)
            append(" \n ")
        }
        log(logMsg.toString())
    }

    private fun log(msg: String) {
        Log.d(tag, msg)
    }
}