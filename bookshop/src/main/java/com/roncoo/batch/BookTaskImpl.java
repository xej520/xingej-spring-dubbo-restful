package com.roncoo.batch;

import org.springframework.stereotype.Component;

@Component
public class BookTaskImpl implements BookTask {

    // 这个方法，是要真正执行的定时任务的逻辑
    @Override
    public void doTask() {
        System.out.println("----定时任务开始处理----");
    }

}
