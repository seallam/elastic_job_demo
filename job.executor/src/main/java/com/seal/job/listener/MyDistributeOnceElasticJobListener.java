package com.seal.job.listener;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.AbstractDistributeOnceElasticJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：
 * 作者：Seal(Seal@lianj.com)
 * 日期：2017年06月22日 上午 11:59
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018
 */
public class MyDistributeOnceElasticJobListener extends AbstractDistributeOnceElasticJobListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MyDistributeOnceElasticJobListener(long startedTimeoutMilliseconds, long completedTimeoutMilliseconds) {
        super(startedTimeoutMilliseconds, completedTimeoutMilliseconds);
    }

    public void doBeforeJobExecutedAtLastStarted(ShardingContexts shardingContexts) {
        logger.debug("before");
    }

    public void doAfterJobExecutedAtLastCompleted(ShardingContexts shardingContexts) {
        logger.debug("after");
    }
}
