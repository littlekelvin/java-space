package com.kelvin.dbmessage.dbmessageconsistent.consistent;

import com.kelvin.dbmessage.dbmessageconsistent.event.MyTransactionEvent;
import com.kelvin.dbmessage.dbmessageconsistent.event.MyTransactionEvent2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FooService {

    private final ApplicationEventPublisher publisher;
    private final FooRepository fooRepository;

    @Transactional
    public boolean saveFoo(FooEntity fooEntity) throws InterruptedException {
        log.error("start insert foo");
        fooRepository.save(fooEntity);
        publisher.publishEvent(new MyTransactionEvent(fooEntity.getFooName()));
        log.error("end insert foo");
        Thread.currentThread().sleep(2000);
        log.error("to commit insert");
        return true;
    }

    @Transactional
    public boolean updateFoo(FooEntity fooEntity) throws InterruptedException {
        log.error("start update foo");
        Optional<FooEntity> foo = fooRepository.findById(fooEntity.getId());
        if (foo.isPresent()) {
            FooEntity oldFoo = foo.get();
            oldFoo.setFooName(fooEntity.getFooName());
            oldFoo.setFooValue(fooEntity.getFooValue());

            publisher.publishEvent(new MyTransactionEvent2(fooEntity.getFooName()));
            publisher.publishEvent(new MyTransactionEvent2(fooEntity.getFooName()));
            publisher.publishEvent(new MyTransactionEvent2(fooEntity.getFooName()));
            log.error("end update foo");
            Thread.currentThread().sleep(2000);
            log.error("to commit update");
        }
        return true;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(MyTransactionEvent event) {
        log.error("after commit then send event {}", event);
        log.error("after commit then send event {}", event.getName());
    }

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void afterCommit2(MyTransactionEvent2 event) {
//        log.error("after commit then send event2 {}", event);
//        log.error("after commit then send event2 {}", event.getName());
//    }
}
