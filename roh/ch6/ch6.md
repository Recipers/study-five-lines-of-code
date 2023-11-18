## 6장 내용 정리

### getter와 setter를 사용하지 말 것
- Boolean이 아닌 필드에 getter와 setter를 사용하지 말 것
  - 왜 Boolean 필드는 예외인가...
- 이름과 관련 없이 필드를 직접 할당하거나 반환하는 메서드를 의미
- getter 메소드 존재 시 캡슐화가 해제되고, 불변속성이 전역적으로 존해하게 됨
- setter 메소드 또한 마찬가지, 내부 데이터 구조를 변경하게 됨
- Tight coupling (강한 결합)
- Pull based Architecture vs Push based Architecture
    - Pull based Architecture
      ```javascript
        class Website {
          constructor (private url: string) { } 
          getUrl() { return this.url; }
        }
            
        class User {
          constructor (private username: string) { }
          getUsername() { return this.username; } 
        }
            
        class BlogPost {
          constructor (private author: User, Private id: string) { }
          getid() { return this.id; } 
          getAuthor() { return this.author; }
        }
        // 데이터를 가져와서 중앙에서 연산을 함
        function generatePostlink(website: Website, post: BlogPost) {
          let ur1 = website.getUrl();
          let user = post.getAuthor();
          let name = user.getUsername();
          let postld = post.getid();
          return ur1 + name + postld;
        }
        ```
        - Push based Architecture
          ```javascript
          class Website {
            constructor (private url: string) { } 
            generatelink(name: string, id: string) {
              return this.url + name + id;
            }
          }
              
          class User {
            constructor (private username: string) { }
            generatelink(website: Website, id: string) {
              return website. generatelink( this.username, id);
            }
          }
              
          class BlogPost {
            constructor (private author: User, Private id: string) { }
            generatelink(website: Website) { 
              return this.author.generatelink(website, this.id);
            }
          }
          // 클래스별로 자신의 기능이 있음, 데이터에 가깝게 연산을 이관함
          function generatePostlink(website: Website, post: BlogPost) {
            return post.generatelink(website);
          }
          ```
- 디미터의 법칙
  - 데메테르 법칙, 최소한의 지식 원칙
  - 낯선 사람에게 말하지 말라
  - 모듈은 자신이 조작하는 객체의 속 사정을 몰라야 한다
  - getter 메소드를 사용하게 되면 직접 접근을 할 수 없어도, 참조를 얻을 수 있어서 내부에 접근 할 수 있게 되므로 해당 원칙 위배

### 공통 접사를 사용하지 말 것
- 코드에는 공통 접두사나 접미사가 있는 메서드나 변수가 없어야 함
- 단일 책임의 원칙
  - 클래스에는 단 하나의 책임만 있어야 함
  - 공통 접사가 있다 === 해당 메서드와 변수가 공통 접사의 책임을 공유하고 있다.
    - 이런 경우에는 각각의 책임을 전담하는 클래스로 분리
- 더 많은 수의 작은 클래스가 도입되는데, 클래스 분리에 소극적이면 안됨

### 순서에 존재하는 불변속성 제거
- 생성자를 통해 초기화를 하면, 순서를 강제화 할 수 있음
- 특정 메서드에서 초기화를 하게 되면 반드시 그 메서드를 호출해야하는데 생성자는 어떤 메서드보다 먼저 호출되기 때문에, 순서 불변속성이 제거됨

### 열거형을 비공개 생성자를 통해 제거
- java와는 거리가 먼 이야기 같음 (Class Type 이라 비공개 생성자를 직접 정의해 줄 수 있음)
