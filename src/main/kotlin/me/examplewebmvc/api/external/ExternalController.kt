package me.examplewebmvc.api.external

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import me.examplewebmvc.api.book.BookController
import me.examplewebmvc.api.external.service.ExtTestService
import me.examplewebmvc.basic.response.BasicResponse
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

/**
 * CompletableFuture 테스트
 */
@Api(value = "ExternalController")
@RestController
@RequestMapping("\${api.base.path}/ext")
class ExternalController (
    val extTestService: ExtTestService
) {
    val logger: Log = LogFactory.getLog(BookController::class.java)

    @ApiOperation(value = "test sync", notes = "test sync")
    @GetMapping("sync")
    fun getOtherResource(
        @ApiParam(value = "repeatCount", example = "5")
        @RequestParam
        repeatCnt: Int
    ): ResponseEntity<Any> {
        return try {
            val list = extTestService.getOtherResourceSync(repeatCnt)

            ResponseEntity(BasicResponse("SUCCESS", list), HttpStatus.OK)
        } catch (e: Exception) {
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }


    @ApiOperation(value = "test async", notes = "test async")
    @GetMapping("async")
    fun getOtherResourceAsync(
        @ApiParam(value = "repeatCount", example = "5")
        @RequestParam
        repeatCnt: Int
    ): ResponseEntity<Any> {
        return try {
            val list = extTestService.getOtherResourceAsync(repeatCnt)

            ResponseEntity(BasicResponse("SUCCESS", list.get()), HttpStatus.OK)
        } catch (e: Exception) {
            logger.error(e.localizedMessage, e)
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "internal server error")
        }
    }
}