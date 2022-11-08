### Requirement
주문생성 후 세가지 일을 수행.
1. 유저에게 주문완료 메일 발송
2. 포인트 적립 시스템에 알림
3. 실시간 주문 통계 시스템에 알림

### Solution
1. with_inheritence package
- ApplicationEvent을 구현받은 SimpleOrderCreateEvent 
- ApplicationListener을 구현받은 SimpleOrderCreateEventListener에서 SimpleOrderCreateEvent를 받아서 처리

2. with_eventlistener package
- @EventListener를 사용하면 ApplicationEvent 및 ApplicationListener를 구현받지 않아도 된다.
- SimpleOrderCreateEventHandlers에서 @EventListener를 사용하여 SimpleOrderCreateEvent를 받아서 처리

3. with_eventlistener_async package
- @Async를 사용하면 비동기로 처리할 수 있다.

### Problem
모두 정상적으로 동작할때는 괜찮다.

하지만 만일 세가지 일을 수행하는 와중 하나가 실패한다면?
- 여기서는 SimpleRealtimeStaticService 에서 실패한다고 가정하자.

1. withannotation package(withinheritence package도 동일)
- 하나가 실패한다면, 추후의 이벤트가 실행되지 않음.
- 트랜잭션이 걸려있다면, 롤백 발생으로, 주문 생성 또한 없던 일이 됨

2. withasync package
- 하나가 실패하더라도, 주문 생성 및 다른 이벤트에는 영향을 주지 않음
- 테스트 실행결과는 그렇지만, 이 부분에 관해 확신하기에는 어려움

### Complementary Solution
1. with_transactional_eventlistner package
- @TransactionalEventListener을 통해 TransactionPhase에 따른 이벤트 발생을 지정할 수 있다.
- 예를들어 좀더 세부적으로 sendPoint는 좀 더 온전한 하나의 작업을 위해 트랜잭션이 완료되기 전에 발생해야한다고 가정하자.