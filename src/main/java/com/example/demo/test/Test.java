package com.example.demo.test;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * Created by yangshengju on 2019-12-6.
 */
public class Test {

    public static void main(String[] args) {
       /* List<EmployeeRoleBean> roleBeanList=Arrays.asList(new EmployeeRoleBean("01416265","ywzc","运维支持"),new EmployeeRoleBean("01416266","ywzc2","运维支持2"),new EmployeeRoleBean("01416267","ywzc3","运维支持3"));
        IntSummaryStatistics intSummaryStatistics=roleBeanList.stream().collect(Collectors.summarizingInt(p->p.getUserName().length()));
        System.out.println(intSummaryStatistics);*/
       //Reactor=jdk8 stream +jdk9 reactive stream
        //Mono 0-1个元素
        //Flux 0-N个元素

        Subscriber<Integer> subscriber1 = new Subscriber<>() {

            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                // 保存订阅关系, 需要用它来给发布者响应
                this.subscription = subscription;

                // 请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                // 接受到一个数据, 处理
                System.out.println("接受到数据: " + item);

                // 处理完调用request再请求一个数据
                this.subscription.request(1);

                // 或者已经达到了目标, 可以调用cancel告诉发布者不再接受数据了
                // this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                // 出现了异常(例如处理数据的时候产生了异常)
                throwable.printStackTrace();

                // 我们可以告诉发布者, 后面不接受数据了
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                // 全部数据处理完了(发布者关闭了)
                System.out.println("处理完了!");
            }

        };

        String[] strs = {"1","2","3"};
        Flux.fromArray(strs).map(s->Integer.valueOf(s))
                //最终操作订阅，
                .subscribe(subscriber1);
    }
}
