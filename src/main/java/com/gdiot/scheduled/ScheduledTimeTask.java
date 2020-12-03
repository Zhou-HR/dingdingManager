package com.gdiot.scheduled;

import com.gdiot.ssm.service.AsyncService;
import com.gdiot.ssm.task.DataSenderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

//@Component
@Slf4j
public class ScheduledTimeTask implements ApplicationRunner {

    @Autowired
    private AsyncService asyncService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1，查出所有电表表号
        // 2，循环，进入线程池。
        // 3,根据表号，当前日期，查前24小时内最早的一条记录和最晚的一条记录，两者相减，得出一天的用电量,保存
        // 注：此处使用注解@Component，开机会执行一次，去掉注解将不执行
        String depId = "1";
        DataSenderTask task = new DataSenderTask(depId, "all_dep_user_detail");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);
    }


}
