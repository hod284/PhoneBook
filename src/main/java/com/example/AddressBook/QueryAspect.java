package com.example.AddressBook;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Aspect
@Component
public class QueryAspect {

/*
execution(*)
→ 모든 메서드를 대상으로 한다.
리턴 타입 상관 없음.
예:
String, void, Optional<Car> 등 전부 포함.

com.example..repository
여기서 중요한 건 .. 부분임.
com.example..repository 는 해석하면:
com.example 패키지 아래의 모든 하위 패키지 중에서
repository라는 이름의 패키지를 찾는다.
즉:
com.example.repository
com.example.AdrressBook.repository
com.example.api.v1.repository
com.example.xxx.yyy.repository
이런 모든 repository 패키지를 포함한다.

* (클래스)
→ repository 패키지 안의 모든 클래스를 의미
예:
UserRepository
CarRepository
PlateRepository
TestRepo
전부 포함된다.

 * (메서드)
→ 그 클래스 안의 모든 메서드를 의미
예:
findById()
findAll()
save()
delete()
getUserByName()
전부 포함.

 ( .. )
→ 파라미터 타입/개수 상관 없음
예:
findById(Long id)
findByName(String name)
save(User user)
getData(int a, String b)
전부 포함됨.
*/

    @AfterReturning(
               value ="execution(*com.example.AdressBook..Repository.*.*(..))",
               returning ="result"
    )
    public void AfterQuery(Object result )
    {
         log.info("커리실행결과" + result.toString());
    }
     
}