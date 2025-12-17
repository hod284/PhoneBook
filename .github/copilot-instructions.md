# PhoneBook Project - AI Coding Agent Instructions

## 프로젝트 개요
Spring Boot 4.0 + PostgreSQL 기반 전화번호부 REST API. 전화 연락처와 통화 이력을 관리합니다.

## 아키텍처 패턴

### 레이어 구조 (패키지명 철자 유지)
- **Contorller/** → REST 엔드포인트 (`/api/*`)
- **Service/** → 비즈니스 로직, `@Transactional` 관리
- **Repository/** → JPA 커스텀 쿼리 (`@Query` JPQL)
- **EntityClass/** → JPA 엔티티 (Lombok `@Getter/@Setter`)
- **Dto/** → Java `record`로 작성 (불변 데이터 전송)
- **ErrrorHandle/** → 현재 비어있음 (에러 처리 구현 예정)

### 엔티티 관계 (중요!)
```java
PhoneNumberBook (부모)        PhoneNumberHistory (자식)
├─ phone_owner (UK, FK 기준)  ├─ phonebook (ManyToOne)
├─ phone_number (UK)          ├─ phone_datetime
├─ phone_group                └─ phone_callingorgettring
└─ histories (OneToMany)
```

**핵심 규칙**:
- `PhoneNumberHistory.phonebook`은 **객체 참조** (String 아님!)
- `@JoinColumn(referencedColumnName = "phone_owner")`로 `phone_owner`를 FK 기준으로 사용
- 부모 삭제 시 `cascade = CascadeType.REMOVE`로 자식 자동 삭제

## 개발 워크플로우

### 빌드 & 실행
```bash
# 로컬 개발
./gradlew bootRun  # Spring Boot 실행 (포트 8081)

# Docker 빌드
./gradlew clean bootJar -x test  # phone.jar 생성
docker build -t hod284/phoneapp:latest .

# 전체 환경 실행 (PostgreSQL + Spring)
docker-compose up -d
```

### 데이터베이스 설정
- **로컬**: `localhost:5432/phone_db` (credentials: `phone_user/phone_pass`)
- **Docker**: `db:5432/phone_db` (컨테이너 네트워크)
- `spring.jpa.hibernate.ddl-auto=update`로 자동 스키마 생성

## 코드 컨벤션

### 1. JPA 쿼리 작성
```java
// ✅ 올바른 방식: @Query + JPQL
@Query("SELECT p FROM PhoneNumberBook p WHERE p.phone_owner = :pname")
PhoneNumberBook GetDatabyName(@Param("pname") String pname);

// ✅ JOIN은 엔티티 관계 활용 (ON 절 불필요)
@Query("SELECT p FROM PhoneNumberHistory p INNER JOIN p.phonebook b WHERE b.phone_owner = :pname")
List<PhoneNumberHistory> GetDatabyNameaslist(@Param("pname") String pname);
```

### 2. DTO는 record 사용
```java
public record ResponseParentDto(String Name, String Number, int Group) { }
```

### 3. Service Layer 트랜잭션
- 쓰기: `@Transactional`
- 읽기: `@Transactional(readOnly = true)`

### 4. Controller 응답 처리
```java
// null/빈 값 체크 후 404 반환
if (re.get(0) == null || re.get(0).isBlank()) {
    return ResponseEntity.notFound().build();
}
return ResponseEntity.ok(re);
```

## AOP 구성
`QueryAspect`가 Repository 메서드 실행 결과를 로깅:
```java
@AfterReturning(value = "execution(* com.example.AdressBook..Repository.*.*(..))", returning = "result")
```
- Repository 패키지의 모든 메서드 대상
- 실행 후 결과를 `log.info`로 출력

## 주석 스타일
코드 내 한글 주석으로 로직 설명:
- 엔티티 클래스: "DB TABLE 에니티 클래스", "springbean 아님 착각하지 말자"
- 복잡한 JPA 관계는 상세 주석 (예: FK 자동 매핑 원리)

## 미완성 영역
- `ErrrorHandle/ErrorHandling.java` 비어있음 (글로벌 예외 처리 추가 필요)
- `static/index.html`, `typescript/main.ts` 비어있음 (프론트엔드 미구현)
- TypeScript 빌드 설정 있으나 통합 안 됨

## 배포 정보
- JAR 파일명: `phone.jar` (`bootJar { archiveFileName = "phone.jar" }`)
- 컨테이너 포트: 8081
- 베이스 이미지: `eclipse-temurin:17-jre`
