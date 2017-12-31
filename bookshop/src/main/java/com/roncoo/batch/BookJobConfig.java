package com.roncoo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

//需要将定时任务，配置成定时的一个job，因此，才创建的这个类
@Configurable
@EnableBatchProcessing
public class BookJobConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    private BookTask bookTask; // 引入 真正执行的业务逻辑

    // 如果添加了batch的话，在程序启动的时候，就会默认将所有的job全部执行了
    // 然后，定时任务才开始，每个3秒钟开始运行

    // Job 是spring batch运行的最基本的单位，运行的就是这些job
    @Bean
    public Job job(Step step1) {
        // step1,从哪一步开始，还可以添加next
        return jobs.get("bookJob").start(step1).build(); // 构建出一个job
    }

    // 这里产生的step，就是要注册到上面job 参数里的step1
    @Bean
    public Step step1() {

        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        adapter.setTargetObject(bookTask); // 设置 执行的目标对象
        adapter.setTargetMethod("doTask");// 执行目标对象里的doTask方法

        return steps.get("bookJobStep1").tasklet(adapter).build();
    }

}
