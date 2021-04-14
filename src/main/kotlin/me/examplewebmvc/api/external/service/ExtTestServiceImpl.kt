package me.examplewebmvc.api.external.service

import me.examplewebmvc.util.CommonUtil
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.stream.Collectors

/**
 * CompletableFuture example
 */
@Service
class ExtTestServiceImpl(
    val taskExecutor: Executor
): ExtTestService {
    val logger: Log = LogFactory.getLog(ExtTestServiceImpl::class.java)

    /**
     * Request taking 1 second
     */
    private fun getResource(): String {
        logger.info("get other resource... (${Thread.currentThread().name})")
        Thread.sleep(1000)
        return CommonUtil.getRandomString(5)
    }

    override fun getOtherResourceSync(repeatCnt: Int): List<String> {
        val list = mutableListOf<String>()

        val start = System.currentTimeMillis()

        for(i in 0 until(repeatCnt)){
            list.add(getResource())
        }
        val end = System.currentTimeMillis()
        logger.info("Total time : ${end - start}")

        return list
    }

    @Async
    override fun getOtherResourceAsync(repeatCnt: Int): CompletableFuture<List<String>> {
        val cfList = mutableListOf<CompletableFuture<String>>()

        val start = System.currentTimeMillis()

        for(i in 0 until(repeatCnt)){
            val cf = CompletableFuture.supplyAsync( {
                getResource()
            }, taskExecutor)
            cfList.add(cf)
        }

        val list = CompletableFuture.allOf(*cfList.toTypedArray()) // future가 모두 완료 되면 CompletableFuture<Void> 반환
            .thenApply{
                // 각 future로부터 받은 데이터 수집
                cfList.stream()
                    .map(CompletableFuture<String>::join)
                    .collect(Collectors.toList())
            }
            .join()

        val end = System.currentTimeMillis()
        logger.info("Total time : ${end - start}")

        return CompletableFuture.completedFuture(list);
    }
}