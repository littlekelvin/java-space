package com.kelvin.myauditing.event;

import com.kelvin.dbmessage.dbmessageconsistent.DbmessageConsistentApplication;
import com.kelvin.dbmessage.dbmessageconsistent.consistent.FooEntity;
import com.kelvin.dbmessage.dbmessageconsistent.consistent.FooService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DbmessageConsistentApplication.class)
public class FooServiceTest {
    @Autowired
    FooService fooService;

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    public void testInsert() {
        FooEntity foo2 = FooEntity.builder()
                .fooName("fooName")
                .fooValue("fooValue")
                .build();
        try {
            fooService.saveFoo(foo2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTrax() throws InterruptedException {
        new Thread(() -> {
            FooEntity foo2 = FooEntity.builder()
                    .fooName("fooName33")
                    .fooValue("fooValue33")
                    .build();
            try {
                fooService.saveFoo(foo2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            FooEntity foo = FooEntity.builder()
                    .fooName("fooName44")
                    .fooValue("fooValue44")
                    .build();
            try {
                fooService.saveFoo(foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Test
    public void testTrax2() throws InterruptedException {
        FooEntity foo = FooEntity.builder()
                .id(15)
                .fooName("fooName111")
                .fooValue("fooValue111")
                .build();
        fooService.updateFoo(foo);
    }

    @Test
    public void testTrax3() throws InterruptedException {
        FooEntity foo = FooEntity.builder()
                .id(15)
                .fooName("fooName3")
                .fooValue("fooValue3")
                .build();
        fooService.updateFoo(foo);
        // if publish event outside transaction, the event will be discarded.
//        publisher.publishEvent(new MyTransactionEvent2(foo.getFooName()));
    }
}