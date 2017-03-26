package com.jd.www.book.algorithm.bigdata;


/**
 * <p>project：study<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:16/12/8 下午4:03</li>
 * <li>function:</li>
 * </ul>
 */
public class buloonFilter {
    //布隆过滤器
    //用2gb内存在20亿个整数中找出出现次数最多的shu
    //40亿个非负数中找到没有出现的数  没有出现的数  操蛋 不知何意
       //解析 1 如果用哈希表保存 如果40亿都不同 存一个整数用40亿 * 4B =160亿字节  16GB
       // 2 如果用bitmap 方式  8个bit 为1B 长度4294967295 占用500m bit 只有 0 和 1
       // 现在只有 10M 内存 只找到一个 没有出现过的数即可，

    //找到100亿个url 中重复url 再进行排序
        // 1 把大文件hash 到小文件，hash 函数保证同一url 分配到同一文件 在进行 统计
        // 2 处理小文件，哈希表统计每种词以及词频，，哈希表记录建立完成后，在遍历hashbiao
        // 3 遍历哈希表过程中使用大小为100 的小根堆选出每个文件的top100，再用外排序或者继续用小根堆 每台机器的top100
        // 4 利用hash函数 分流 和 利用hash表来统计词频， 经常利用 堆结构 和 外排序进行处理


    //一致性hash
        //问题 服务器集群缓存，常见是 以服务器数量取莫 看看key%n

    long size = 4294967295l;

    static byte[] bytes = new byte[2];




}
