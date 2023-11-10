## 5장 내용 정리

- 유사한 클래스 통합
    - 상수 메서드 (constant method) : 메서드가 상수를 반환하는 경우
    - 일련의 상수 메서드의 집합 : 접점 (basis)
      - 일련의 상수메서드가 2개 일 때, 2개의 접점을 가진 기준
    - **X 개의 클래스를 통합하려면 최대 X-1개의 접점을 가진 기준이 필요함**  
      ```javascript
      function nextColor(t: TrafficColor) {
        if (t.color() === "red") return new Green();
        else if (t.color() === "green") return new Yellow(); 
        else if (t. color() == "yellow") return new Red();
      }
      
      interface TrafficColor { 
        color(): string; 
        check(car: Car) : void;
      }
      
      class Red implements TrafficColor {
        color() { return "red"; }
        check(car: Car) { car.stop(); } 
      }
      
      class Yellow implements TrafficColor { 
        color() { return "yellow"; } 
        check(car: Car) { car.stop(); }
      }
      
      class Green implements TrafficColor {
        color() { return "green"; }
        check(car: Car) { car.drive(); } 
      }
      ```

- 복잡한 조건 통합
  - 산술 규칙 사용
    - 결합법칙, 분배법칙, 교환법칙
  - 순수 조건
    - 순수조건이란 **부수적인 동작이 없는 조건**
    - 부수적인 동작이란 변수에 값을 할당하거나 I/O와 상호작용 하는 것
    - 조건에 부수적인 동작이 있을 경우가 흔치 않기 때문에 예측하기 어려울 수 있음
  - 명령에서 질의 분리


- UML 클래스 다이어그램
    - **전략패턴**은 클래스 다이어그램을 통해 보여짐
    - 다양한 유형이 있음
      - 시퀀스 다이어그램
      - 클래스 다이어그램
      - 활동 다이어그램

- 전략 패턴
    - 다른 클래스를 인스턴스화 해서 **변형**해서 도입하는 개념
    - 전략 패턴이 필드를 가지고 있으면 상태 패턴이라고 함
    - 코드에 변형을 도입하고 싶어서 리팩토링을 수행하는 경우
    - 떨어지는 성질을 코드화했던 상햄서 바로 변형 의 추가가 필요하다고 예상하지 않았을 때
      - 단지 클래스 간의 동작을 통합하려는 경우

- 구현체가 하나뿐인 인터페이스는 만들지 말 것
  - 가독성에 도움이 되지 않음
  - 하나의 구현 클래스만 존재하는 인터페이스를 즐겨 쓴 코드베이스의 경우 필요한 것 보다 두배 많은 파일이 필요함
    - [스프링 ServiceImpl이 대표적인 예시가 아닐까](https://wildeveloperetrain.tistory.com/49)
  - 아무 구현체가 없는 인터페이를 갖는 것이 합리적일 때가 있음
    - java의 경우 Serializable([마커 인터페이스](https://kjhoon0330.tistory.com/entry/Java-%EB%A7%88%EC%BB%A4-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4%EC%97%90-%EB%8C%80%ED%95%98%EC%97%AC)) 같은 인터페이스에 해당하는 이야기인지?