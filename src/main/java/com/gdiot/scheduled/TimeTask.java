package com.gdiot.scheduled;

import com.gdiot.ssm.service.AsyncService;
import com.gdiot.ssm.task.DataSenderTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ZhouHR
 */
@Component
@Slf4j
public class TimeTask {

    @Autowired
    private AsyncService asyncService;

    /**
     * 定时任务每天凌晨1点执行一次，查询所有的用户信息
     * 1，获取所欲部门ID及子部门ID
     * 2，获取部门下的用户ID列表
     * 3，根据用户ID获取用户详情
     * 4，用户详情解析，保存数据库
     */
//	@Scheduled(cron = "0 0 0 * * ?")//每天凌晨执行一次
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨1点执行一次
    public void scheduledTask() {
        log.info("定时任务每天凌晨1点执行一次，查询所有的用户信息");
        String depId = "1";
        DataSenderTask task = new DataSenderTask(depId, "all_dep_user_detail");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);
    }

    /**
     * 定时任务每天凌晨2点执行一次
     * 查询用户的所有上级部门ID，保存数据库
     */
//	@Scheduled(cron = "0 0 0 * * ?")//每天凌晨执行一次
//	@Scheduled(cron = "0 0 2 * * ?")//每天凌晨2点执行一次
    public void scheduledTask2() {
        log.info("定时任务每天凌晨2点执行一次，查询用户的所有上级部门ID，保存数据库");
        String depId = "1";
        DataSenderTask task = new DataSenderTask(depId, "all_user_parent_dep");
        task.setAsyncService(asyncService);
        asyncService.executeAsync(task);
    }

    @Scheduled(cron = "0 0 0/1 * * ?") //每个整点执行一次：
    public void testTask() {
        log.info("定时任务每小时执行一次");
    }

    //  @Scheduled(cron = "30 0/10 * * * ?") //10分钟一次
    public void testTask10() {
        log.info("定时任务每10分钟执行一次");
    }
}
