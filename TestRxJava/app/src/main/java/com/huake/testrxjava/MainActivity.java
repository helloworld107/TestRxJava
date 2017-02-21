package com.huake.testrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huake.testrxjava.bean.Course;
import com.huake.testrxjava.bean.Student;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        method1();
//        method2();//简化
//        method3();//进一步简化
//        method4();//转换
//        operate();
//        method5();
        method6();
    }

    private void method6() {

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("english"));
        courses.add(new Course("math"));
        courses.add(new Course("text"));
        ArrayList<Student> students = new ArrayList<>();
        Student student1= new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        student1.setCourse(courses);
        student2.setCourse(courses);
        student3.setCourse(courses);
        students.add(student1);
        students.add(student2);
        students.add(student3);
       Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
           @Override
           public Observable<Course> call(Student student) {
               return Observable.from(student.getCourse());
           }
       }).subscribe(new Action1<Course>() {
           @Override
           public void call(Course course) {
               System.out.println(course.getName());
           }
       });

    }

    private void method5() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("sfasfda");
        strings.add("qwertrt");
        String[] datas={"qwe","qweqe","wqewqeqw"};
        Observable.from(datas).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);

            }
        });
    }

    private void method4() {
        Observable.just("hello").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"888";
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private void method3() {
        Observable.just("hellosos").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    private void method2() {
        Observable<String> observable = Observable.just("hello");
        Action1<String> action1 = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(action1);
    }

    private void method1() {

        //这样写显然比较繁琐，可以进一步简化
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        observable.subscribe(subscriber);
    }

    private void operate() {

        //创建被监听者、数据源
        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onNext(4);
                subscriber.onNext(5);
                subscriber.onCompleted();
            }
        });
        //创建监听者
        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer value) {

                System.out.println(value+"");
            }
        };

        //绑定数据源和观察者
       observable.map(new Func1<Integer, Integer>() {
           @Override
           public Integer call(Integer integer) {
               return integer*10;
           }
       }).subscribe(subscriber);

        ArrayList<Integer> datas = new ArrayList<>();
        datas.add(1);
        datas.add(1);
        datas.add(1);
        for (Integer data : datas) {
            System.out.println(data);

        }
    }


}
