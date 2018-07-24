package com.paper.approve.timedTask;

import com.paper.common.util.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class Book {


    // 获取 位置 及 书架号
//    @Scheduled(cron = "0 */15 * * * ?")
    public void getBook(){
        System.out.println("执行获取书架号定时任务" + DateUtils.getDate());
        String host = "http://weixin.cqshusheng.cn";
        String path = "/activity/getBookInfo";

        String path1 = "/activity/getBookshelf";


    }

    // 获取评论
//    @Scheduled(cron = "0 */17 * * * ?")
    public void comment(){
        System.out.println("执行获取评论定时任务" + DateUtils.getDate());
        String host = "http://weixin.cqshusheng.cn";
        String path = "/activity/getComment";

    }


}
