## 7장 내용 정리

### 컴파일러 특징

1. 컴파일러는 런타임 동안 어떤 일이 일어날지 정확하게 말할 수 없다
    - 정지 문제 (halting problem)
        - 주어진 프로그램이 해결하고자 하는 문제를 해결하는지 말해줄 수 있는 일반화된 알고리즘이 존재하는가?
        - 일반적으로 프로그램은 근본적으로 예측이 불가능
      ```javascript
      // 35는 절대 반환되지 않고 아래 코드가 실행되지도 않는다는 것을 알고 있다.
      if (new Date().getDay() === 35)
        5.foo();
      ```
    - 컴파일러는 런타임 중 실패를 포함해서 예상대로 동작하지 않는 프로그램이라고 해도 허용해야함
    - 반대로 프로그램이 안전하다고해도 프로그램을 허용하지 않는 경우도 있다 -> 보수적 분석 (conservative analysis)

2. 컴파일러는 메서드의 반환을 보장한다
    - 메서드가 모든 경로 (if, switch 등)에서 반환이 되는지 검사함

3. 컴파일러는 초기화되지 않는 변수에 대한 접근을 막는다
    - 변수가 사용되기 전에 변수에 값이 확실히 할당되었는지 여부를 알아낼 수 있음
    - if문을 사용해서 지역변수를 초기화 하려는 경우 모든 경로에서 변수를 초기화한다는 보장이 없음
   ```javascript
   // 다음과 같은 코드는 컴파일러가 허용하지 않는다
   let result;
   for (let i = 0; i <arr.length; i++)
    if (arr[i].name === ”John”)
        result = arr[i];
   return result;
   ```

4. 접근제어로 데이터 캡슐화를 지원
    - 비공개(private)로 하면 오용되지 않을 것이란 확신을 할 수 있음
   ```javascript
   class Class {
     private sensitiveMethod() {
     }
   }
   let c = new Class();
   c.sensitiveMethod(); // 컴파일러 오류 발생
   ```

5. 타입 검사로 속성을 보증함
    - 책에서 소개하는 컴파일러의 가장 강력한 기능
    - 변수와 멤버가 존재하는지 확인하는 역할
    - 순서 강제화를 가능하게 한 것도 타입 검사
   ```javascript
   interface NonEmptyList<T> {
     head: T;
   }
   class Last<T> implements NonEmptylist<T> {
     const uctor(public readonly head: T) { }
   }
   class Cons<T> implements NonEmptyList<T> {
     constructor(
       public readonly head: T,
       public readonly tail: NonEmptyList<T>) { }
   }
   function first<T>(xs: NonEmptyList<T>) {
       return xs.head;
   }
   first([]); // 타입 오류
   ```
    - 언어별 타입 검사의 강도가 높은 순서대로
        - 대여 타입(Rust)
        - 다형성 타입 유추(OCaml과 F#)
        - 타입 클래스(Haskell)
        - 유니언 타입과 교차타입(타입스크립트)
        - 종속타입(Coq와 Agda)

6. 컴파일러는 null을 역참조에 대한 확인을 할 수 없다
    - null로 메서드를 호출하려고 하면 오류가 발생
    - 많은 현대 언어에서 null로 메서드를 호출하는 것을 감지할 수 없음
    - null 검사는 너무 많이 적게 확인하는 것 보다 너무 많이 확인하는 것이 나음

7. 컴파일러는 산술 오류, 오버플로우 여부를 확인할 수 없다
    - 어떤 컴파일러도 잠재적인 0으로 나누기를 발견하지 못함
    - 오버플로 또는 언더플로를 유발할 만람 큰 숫자를 더하거나 빼지 않아야 하며 그래야 할 경우 Biglnteger와 그 변형을 시용

8. 컴파일러는 아웃 오브 바운드 에러를 확인할 수 없다
    - 컴파일러가 곤란해하는 또 다른 경우는 우리가 직접 데이터 구조에 접근할 때
    - 데이터 구조의 범위 내에 있지 않은 인덱스에 접근하려고 하면 아웃-오브-바운드(out of bounds) 오류가 발생

9. 컴파일러는 무한루프를 볼 수 없다
    - 이런 문제는 while에서 for, 그리고 foreach로, 최근에는 타입스크립트의 forEach와 자바의 stream 연산자, C#의 LINQ 같은 더 높은수준의
      구성으로 전환함으로써 감소

10. 교착 상태 및 경쟁 상태로 인해 의도하지 않은 동작이 발생
    - 멀티스레딩에서 비롯됨
    - 두 개 이상의 스레드가 공유하는 변수를 읽고 쓰기 위해 경쟁할 때 발생
    - 변경 가능한 데이터를 공유하는 다중 스레드를 사용하지 말라

### 컴파일러 활용하기

1. TODO 리스트를 사용해서 안정성 확보
    - 컴파일러가 어떤 참조도 놓치지 않는다는 것을 이용하는 방법
2. 순서 강제화를 이용한 안전성 확보
    - 순서 강제화 패턴은 프로그램의 불변속성에 대해 컴파일러에게 알려주는데 집중해서 불변속성을 일반 속성으로 만들 수 있음
    - 컴파일러가 컴파일 할 때마다 속성이 항상 유지되도록 보장하기 때문에 이후로 불변속성이 실수로 깨지는 일은 없을 것
3. 캡슐화 강제를 통한 안전성 확보
    - 엄격한 캡슐회를 강제하기 위해 컴따일러의 접근 제어를 사용해서 불변속성을 지역화
    - 데이터를 캡슐화하면 데이터가 기대하는 형태로 유지되는 것을 보장
4. 컴파일러로 사용하지 않는 코드 감지
    - 컴파일러가 직접 사용하는 코드인지 아닌지는 감지할 수 없음
    - 인터페이스에서 메서드 삭제를 함으로써 실제로 사용되는 코드인지 아닌지 식별 가능
5. 확정 값을 통한 안전성 확보
    - 컴파일러의 확정 할당 분석에 의존
    - 생성자가 종료될 때 읽기 전용 필드에는 값이 할당돼 있어야 함

### 컴파일러를 잘못 사용하는 사례

- 타입 검사 속이기
    1. 형 변환
        - 형 변환은 컴파일러에게 컴파일러보다 자신이 더 잘 알고있다고 말하는것과 같음
        - 형 변환이 필요하다 === 타입에 대한 이해를 잘 하지 못하고 있다
       ```javascript
       let num = <number〉 JSON.parse(variable);
       // variable이 항상 number로 캐스팅 될 수 있는지에 대한 확신이 있는가
       ```
    2. 동적 타입
        - 컴파일러의 타입 검사를 비 활성화 하는 방법
        - 타입스크립트의 any, C#의 dynamic
        - java로 따지면 Object? Wildcard?
    3. 런타임 타입
        - 런타임으로 판단에 필요한 정보를 옮기는 방법
       ```javascript
       function stringConstructor(conf: Map<string, string>, parts: string[]) {
         return conf.get("Prefix") + parts.join(conf.get("joiner")) + conf.get("postfix");
       }
       ```
        - jackson library의 LinkedHashMap?

- 개발자의 게으름
    1. 기본값
        - 기본 값을 사용하는 곳마다 결국 다른 누군가가 기본 값으로 넣지 말아야 할 값을 추가하고 수정하는 것을 잊어버릴 것
        - 기본 값을 사용하는 대신 개발자가 무언가를 추가하거나 변경할 때 마다 직접 처리하게 할 것
    2. 상속
        - 인터페이스에서만 상속 받을 것
        - 구현 클래스간의 결합을 발생시킴
    3. 처리를 강제하지 않은 예외
        - 예외가 발생한다면 예외를 처리하거나 최소한 호출자에게 예외가 처리되지 않았음을 알려야 함
        - 처리를 강제한 예외 (Checked Exception)을 사용할 것
        - 책의 내용과 반대(?) 되는 의견들...
            - [Java의 Checked Exception은 실수다?](https://velog.io/@eastperson/Java%EC%9D%98-Checked-Exception%EC%9D%80-%EC%8B%A4%EC%88%98%EB%8B%A4-83omm70j)
            - [The Trouble with Checked Exceptions A Conversation with Anders Hejlsberg](https://www.artima.com/articles/the-trouble-with-checked-exceptions)

- 아키텍처에 대한 이해 부족
    - 마이크로 아키텍처
        - 팀에 영향을 미치지만 다른팀에는 영향을 주지 않는 아키텍처
        - 주로 getter와 setter를 통해 캡슐화를 깨는데서 비롯함
        - 수신측과 필드 사이에 결합이 생겨버리고, 컴파일러가 접근을 제어하지 못하게 됨

### 컴파일러를 신뢰

1. 컴파일러에게 불변속성 가르치기
    - 아래의 코드는 컴파일 되지 않음
    - randomElement 함수에서 total이 총 멤버의 수이고, 항상 멤버가 선택되어 반환된다는 사실을 컴파일러는 알 수 없음
        ```javascript
        class CountingSet {
          private data: StringMap<number〉 = {}
          private total = 0;
          add(element: string) {
            let c = this.data.get(element);
            if (c == undefined)
              c =0;
            this.data.put(element, c + 1);
            this.total++;
          }
      
          randomElement(): string {
            let index= randomint(this.total);
            for (let key in this.data.keys()) {
              index -= this.data[key];
              if (index <= 0)
                return key;
            }
            // 도달성으로 인한 오류 발생 (return이 없음)
            // throw new Impossible(); 예외를 던져줌으로써 불변속성이 깨지지 않도록 조치
          }
        }
        ```
2. 컴파일러의 경고에 주위를 기울이기
