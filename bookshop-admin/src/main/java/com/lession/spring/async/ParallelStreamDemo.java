package com.lession.spring.async;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Stream;



/**
 * JDK8,新特性，并行流
 * 
 * 主要是用来做计算密集型的并行处理的
 * 
 * 实际上，用的场景并不是很多
 * 
 * @author erjun
 * 2018年1月3日 上午5:38:59
 */
public class ParallelStreamDemo {
    
    //需求：sum=1+2+3+。。。+n
    //并行流，求和
    // 注意:并不是 并行方式，要比 串行方式效率高
    // 一定要测试数据后，才可以下结论的
    // 下面是并行流方式(多个线程)
    public static long parallelSum(long n){
        return Stream.iterate(1L, i -> i+1)
                    .limit(n)
                    .parallel()
                    .reduce(0L, Long::sum);
    }
    
    //顺序流，一个线程内完成的
    public static long sequenceSum(long n){
        return Stream.iterate(1L, i -> i+1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    
    //迭代，老方法
    public static long iterativeSum(long n) {
        long result = 0;
        
        for(int i= 1; i <=n; i++) {
            result += i;
        }
        
        return result;
        
    }
    
    //每种算法做10次，然后取最小的一次，作为这种策略的成绩
    public static long test(Function<Long, Long> computer, long n) {
        long fastest = Long.MAX_VALUE;
        
        for(int i = 0; i < 10; i++) {
            long start = System.currentTimeMillis();
            
            computer.apply(n);
            
            long cost = System.currentTimeMillis() - start;
           
            if (cost < fastest) {
                fastest = cost;
            }
        }
        
        return fastest;
    }
    
    
    public static void main(String[] args) {
        long n = 20_000_000;//2千万的求和
        System.out.println("顺序流:"+test(ParallelStreamDemo::sequenceSum, n));
       
        System.out.println("并行流:"+test(ParallelStreamDemo::parallelSum, n));
        
        System.out.println("迭代:"+test(ParallelStreamDemo::iterativeSum, n));
    }
    
    /**
     * 测试结果：
     *  
     *  迭代最快，顺序流次之，并行流最慢
     *  
     *  为什么会有这种形象呢？
     * 
     *  第一、Stream中的iterate方法中，有装箱，拆箱的动作，即将Long类型，转换成long类型后，才能求和的
     *  
     *  第二、iterate 方法，很难分成独立的块并行处理的，因为里面的数据结构是一个链表，而不是ArrayList
     *  
     *  因为i->i+1 这个函数，都要依赖前一次计算的结果，有一个lamb表达式来计算下一次的元素，
     *  
     *  并不能很有效进行处理并行流式处理
     */
    
}
