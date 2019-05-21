package com.country.exchange.batch;

import com.country.exchange.service.ExchangeBatchService;
import com.google.common.base.Stopwatch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class ExchangeDataBatch {
    private final ExchangeBatchService exchangeBatchService;

    // free plan 은 1시간 단위지만 basic plan 이라는 가정하에 1분 배치
    @Scheduled(cron = "0 0/1 * * * *")
    public void sync() {

        log.info("ExchangeDataBatch syncData start");

        Stopwatch stopWatch = Stopwatch.createStarted();

        exchangeBatchService.syncData();
        log.info("ExchangeDataBatch syncData end, time={}", stopWatch.stop());
    }

}
