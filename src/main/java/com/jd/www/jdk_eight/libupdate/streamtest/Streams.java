package com.jd.www.jdk_eight.libupdate.streamtest;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>project：javabruce<p>
 * <ul>
 * <li>author:崇汉(chonghan)</li>
 * <li>time:17/2/21 下午2:51</li>
 * <li>function:</li>
 * </ul>
 */
public class Streams {
    private enum Status{
        OPEN,CLOSED
    }

    private static final class Task{
        private final Status status;
        private final Integer points;

        Task(final Status status,final Integer points){
            this.status = status;
            this.points = points;
        }

        public Integer getPoints(){
            return points;
        }

        public Status getStatus(){
            return status;
        }


        @Override
        public String toString() {
            return "Task{" +
                    "status=" + status +
                    ", points=" + points +
                    '}';
        }



    }


    public static void main(String[] args) {
        final Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN,3),
                new Task(Status.CLOSED,23),
                new Task(Status.OPEN,8)
        );


        tasks.forEach(System.out::println);


        System.out.println("----------------------------");

        Collection<Task> cl1 = tasks.parallelStream().filter(task->task.getStatus()==Status.CLOSED).collect(Collectors.toList());

        System.out.println("the close is cl1:"+cl1);

        //第一个问题，查看一共有多少个open 以前 用foreach 循环遍历task集合 java 8 可以使用 streams解决
        final long totalPointsOfOpenTasks = tasks.stream().filter(task -> task.getStatus() == Status.OPEN)
                .mapToInt(Task::getPoints)
                .sum();

        System.out.println("total points:"+totalPointsOfOpenTasks);


        final double totalPoints = tasks.stream().parallel().map(task->task.getPoints()).reduce(0,Integer::sum);

        System.out.println("total points (all tasks):"+totalPoints);


        final Map<Status,List<Task>> map = tasks.stream().parallel()
                .collect(Collectors.groupingBy(Task::getStatus));

        System.out.println("group by :"+map);


        //计算集合中每个任务的点数在集合中所占比重，
        final Collection<String> result = tasks.stream().mapToInt(Task::getPoints).asLongStream()
                .mapToDouble(points->points/totalPoints)
                .boxed()
                .mapToLong(weigth->(long)(weigth*100))
                .mapToObj(percentage -> percentage +"%")
                .collect(Collectors.toList());


        System.out.println(result);



    }
}
