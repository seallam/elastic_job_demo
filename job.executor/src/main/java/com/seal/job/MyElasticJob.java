package com.seal.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 功能：
 * 作者：Seal(Seal@lianj.com)
 * 日期：2017年06月22日 上午 11:26
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018
 */
public class MyElasticJob implements SimpleJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Integer shardingTotalCount;

    private String cron;

    private String shardingItemParameters;

    private String description;

    public void execute(ShardingContext shardingContext) {
        logger.debug("123");
        switch (shardingContext.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                logger.debug("sharding 0");
                break;
            case 1:
                // do something by sharding item 1
                logger.debug("sharding 1");
                break;
            case 2:
                // do something by sharding item 2
                logger.debug("sharding 2");
                break;
            // case n: ...
        }
    }

    public Integer getShardingTotalCount() {
        return shardingTotalCount;
    }

    public void setShardingTotalCount(Integer shardingTotalCount) {
        this.shardingTotalCount = shardingTotalCount;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getShardingItemParameters() {
        return shardingItemParameters;
    }

    public void setShardingItemParameters(String shardingItemParameters) {
        this.shardingItemParameters = shardingItemParameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
