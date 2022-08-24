package com.rc.simpleserver.controllers.interceptors

import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter
import java.io.BufferedReader
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class LoggerInterceptor : HandlerInterceptorAdapter() {
    private val logger = KotlinLogging.logger {}

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        try {
            val requestBody = request.reader.use { it.readText() }

            logger.info { ">>> Payload[$requestBody]" }
            logger.info { ">> [${request.method}] ${request.requestURI} " }
            //TODO log request parameters
        } catch (ex: IOException) {

        }

        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        logger.info { ">> [${request}]" }

        super.postHandle(request, response, handler, modelAndView)
    }
}
