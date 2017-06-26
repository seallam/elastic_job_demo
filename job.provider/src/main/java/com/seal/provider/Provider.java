package com.seal.provider;

import ch.qos.logback.ext.spring.ApplicationContextHolder;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.seal.job.MyElasticJob;
import com.seal.job.listener.MyDistributeOnceElasticJobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：
 * 作者：Seal(Seal@lianj.com)
 * 日期：2017年06月22日 上午 11:28
 * 版权所有：广东联结网络技术有限公司 版权所有(C) 2016-2018
 */
public class Provider {

    private static Logger logger = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) {
        try {
            String profile = "development";
            if (args != null && args.length > 0) {
                profile = args[0];
            }
            System.setProperty("spring.profiles.active", profile);
            long startTimeoutMills = 5000L;
            long completeTimeoutMills = 10000L;
//            new ClassPathXmlApplicationContext("conf/context.xml");
            new JobScheduler(getRegistryCenter(), createJobConfiguration(), new MyDistributeOnceElasticJobListener(startTimeoutMills, completeTimeoutMills)).init();
            logger.info(new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]").format(new Date()) + " service server started!");
        } catch (RuntimeException e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static CoordinatorRegistryCenter getRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration("47.88.171.155:2181", "elastic-job-demo");
        return new ZookeeperRegistryCenter(zkConfig);
    }

    private static LiteJobConfiguration createJobConfiguration() {
        // 创建作业配置
        MyElasticJob simpleElasticJob = ApplicationContextHolder.getApplicationContext().getBean("simpleElasticJob", MyElasticJob.class);
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder(simpleElasticJob.getDescription(), simpleElasticJob.getCron(), simpleElasticJob.getShardingTotalCount()).shardingItemParameters(simpleElasticJob.getShardingItemParameters()).build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(coreConfig, MyElasticJob.class.getName());
        LiteJobConfiguration result = LiteJobConfiguration.newBuilder(simpleJobConfiguration).build();
        return result;
    }
}
