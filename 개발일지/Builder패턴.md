# Builder 패턴

생성자에 인자가 많을 때는 빌더 패턴을 고려해보란 글을 봐서, 회원가입 시에 빌더 패턴을 적용시켜 보았다.

**Builder Pattern**이란 복합 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 패턴이다. (출처: 위키백과)

*기존코드*

```java
MemberSignupRequestDto request = new MemberSignupRequestDto(form.getEmail(), form.getPassword(), form.getName(), form.getRole(), form.getNickname());
```

*수정된 코드*

```java
MemberSignupRequestDto request = MemberSignupRequestDto.builder()
                 .email(form.getEmail())
                 .name(form.getName())
                 .nickname(form.getNickname())
                 .password(form.getPassword())
                 .role(form.getRole())
                 .build();
```

* 최근에 dart언어를 배우면서,, 생성자에 required 필드를 넣어 named parameter를 사용할 때,어떤 값에 무엇이 들어가는 지 보여서 편하다고 생각했는데 빌더 패턴을 사용하니 그 편함을 가져온 기분이였다.

*dart named parameter*

```dart
void main(){
    int result1 = addNumbers(x: 10, y: 20);
}

int addNumbers({
    required int x,
    required int y,
    int z = 30, // required 없으면 optional
}){
    int sum = x + y + z;
    return sum;
}
```

* 현재는 생성자가 form에서 데이터를 가져오는 형식이여서 그다지 불편함이 없어보이지만, 직접 값을 넣는다치면 이것이 어떤 필드에 들어가는 값인지 불분명해지는 불편함이 있을 것이다.

* 그럴 때 빌드 패턴을 쓰면 개발자간에 서로 좋을 듯!

*예시코드*

```java
MemberSignupRequestDto member = MemberSignupRequestDto.builder()
                         .name("kim")
                         .email("ab@cd")
                         .password("1234")
                         .nickname("hi")
                         .role(Role.USER)
                         .build();
```



## Builder 패턴 적용 방법

* 롬복 라이브러리를 사용하면 Builder 패턴을 쓰고 싶은 클래스 위에 @Builder 어노테이션만 달아주면 된다.

* [직접 구현하는 방법]([java-design-patterns/builder at master · greekZorba/java-design-patterns · GitHub](https://github.com/greekZorba/java-design-patterns/tree/master/builder))


