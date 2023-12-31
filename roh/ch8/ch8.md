## 8장 내용 정리

### 주석 자제하기

- 메서드의 내부에 있으면서 Javadoc과 같은 외부 도구에서 사용하지 않는 주석을 대상
- 좋은 주석을 작성히는 방법을 연구하는 프로그래머는 많지 않아서 코드의 가치를 오히려 떨어뜨리게 됨

> [주석은] 미묘한 문제로 감각과 판단력이 필요합니다. 저는 몇 가지 이유로 주석을 없애는 것이 더 좋을 수 있다고 생각합니다.  
> 첫째. 코드가 명확하고 좋은 타입병과 변수명을 사용하는 경우 에는 그 자체가 주석이 됩니다.  
> 둘째, 주석은 컴파일러가 확인하지 않으므로 특히 코드가 수정 된 후에는 주석이 맞다고 보장할 수 없습니다. 오해의 소지가 있는 주석은 매우 혼란스러울 수 있습니다.  
> 셋째, 타이포그래피 문제로서 주석은 코드를 복잡해 보이게 합니다.  
> -롭파이크

> - 일반적인 문제는 이해할 수 없는 코드를 작성한 작성자가 주석으로 어떻게든 명확하고 분명하 게표현할수 있다고 생각한다는 것입니다.
> - 코드가 표현할수 없는 것만 주석으로 처리하십시오.  
> -케블린 헤니

- 주석은 컴파일러가 검사하지 않으므로 제약 조건이 없어 코드보다 작성하기 쉬움
- 컴파일러가 검사하지 못하므로 오히려 관련성이 없거나, 완전히 다른 내용이 들어가 오해의 소지가 생길 수 있음

1. 오래된 주석 제거
2. 주석 처리된 코드 제거
    - 일부 코드를 제거하고 테스트하는 경우는 있을 수 있음
    - 테스트 후에는 삭제 해야됨
    - 버전 관리를 통해 쉽게 복구할 수 있기 때문
3. 불필요한 주석 제거
4. 메서드의 이름으로 주석 대신하기
    - 일부 주석은 기능보다 코드를 문서화함
    - 계획을 위한 주석을 사용하는 것은 로드맵을 만들 때는 좋은 방법이지만 구현이 되고 나면 불필요해짐
5. 불변속성을 문서화한 주석 유지
    - 이 주석으로 누군가 버그를 만드는 것을 막을 수 있는가? 라고 자문 해보고
    - 불변속성을 검증하기 위한 자동화된 테스트를 만들수 있는지 생각해 봤을 때
    - 두 가지 모두 실행할 수 없다고 판단되면 주석을 유지
   ```javascript
    /// 다음 요청 때 강제로 재인증하도록 하는 로그아웃
    session. logout();
   ```
    - TODO, PROBABLY, FIXME 등과 같은 주석은 프로세스의 불변속성
        - 시각적으로 표시해 줄 필요가 있으며 제거하기 위해 노력해야 됨

### 책에서 주석을 자제하라는 내용의 예시

- [https://code.jquery.com/jquery-3.0.0.js](https://code.jquery.com/jquery-3.0.0.js)
  ![jquery 주석 발췌](./jquery%20주석%20발췌.png)