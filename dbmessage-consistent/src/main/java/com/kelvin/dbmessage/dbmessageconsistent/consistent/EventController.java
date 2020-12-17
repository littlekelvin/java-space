package com.kelvin.dbmessage.dbmessageconsistent.consistent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final FooService fooService;

    @GetMapping("foo")
    public String saveFoo() {
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
        return "success";
    }
}
