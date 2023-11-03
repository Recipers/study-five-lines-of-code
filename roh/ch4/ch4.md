## 4장 내용 정리

- if 문에서 else를 사용하지 말것

  - 독립된 if문 : check
  - if-else : decision
  - early binding
    > 동작이 컴파일 시 처리됨  
    > 재컴파일 없이 수정할 수 없음  
    > **추가에 의한 변경을 방해함**
  - late binding
    > 동작이 런타임에 처리됨  
    > 객체 지향이라는 강력한 흐름제어를 이용
  - [early binding vs late binding in java](https://www.geeksforgeeks.org/difference-between-early-and-late-binding-in-java)
    - early binding
      ```java
      public class NewClass {
        public static class superclass {
            static void print() {
                System.out.println("print in superclass.");
            }
        }
        public static class subclass extends superclass {
            static void print() {
                System.out.println("print in subclass.");
            }
        }
        public static void main(String[] args) {
            superclass A = new superclass();
            superclass B = new subclass();
            A.print();
            B.print();
        }
      }
      ```
      > print in superclass.  
      > print in superclass.
    - late binding
      ```java
      public class NewClass {
          public static class superclass {
              void print() {
                  System.out.println("print in superclass.");
              }
          }
          public static class subclass extends superclass {
              @Override
              void print() {
                  System.out.println("print in subclass.");
              }
          }
          public static void main(String[] args) {
              superclass A = new superclass();
              superclass B = new subclass();
              A.print();
              B.print();
          }
      }
      ```
      > print in superclass.  
      > print in subclass.

- 불필요한 메서드 인라인화

  - 호출하는 측을 단순화 하는 작업
  - 인라인화 시 너무 복잡하지 않은지 확인
  - **호출 또는 전달, 한 가지만 할 것 규칙을 위반하지 않도록**

- 일반성 제거

  - 일반화 하고 재사용하려는 본능적인 욕구에서 비롯됨
  - **책임이 흐려짐**
  - 다양한 위치에서 호출할 수 있기 때문에 문제가 됨
  - 전문화된 메서드는 더 적은 위치에서 호출되고, 필요성이 없어지면 더 빨리 지울 수 있음

- switch를 사용하지 말 것

  - default 케이스가 없고 모든 case에 반환값이 있는 것이 아니라면 switch 사용 금지
  - default 키워드로 인해 **어떤 case가 처리되고 어떤 case가 처리되지 않는지에 대하여 컴파일러를 통해 판단 불가능 (불변속성)**
  - break 키워드를 만나기 전까지 case를 연속해서 실행하는 fall-through 로직 (break 누락 우려)
  - [if else vs switch vs when(kotlin) in java](https://dev.gmarket.com/92)

- 인터페이스 대신 추상클래스는요?
  - 사용할 수는 있다
  - 실수로 속성을 잊어버릴 수 있음
  - 해서는 안되는 오버라이딩을 할 수 있음
  - **인터페이스에서만 상속받을 것**
