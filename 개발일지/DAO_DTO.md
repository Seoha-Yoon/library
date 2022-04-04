# DAO와 DTO

스프링 개발 예제를 찾아보다보니까 DTO 클래스를 많이 사용하는 것을 확인했다.

도서관 웹 서비스 예제에도 DTO가 적용되어 있지만.. 잘 이해가 되지 않아 제대로 다시 정리해보려 한다.
<br></br>


* **DAO(Data Access Object)**
  
  * 데이터베이스의 data에 접근하기 위한 객체
  
  * DB에 접근하기 위한 로직 및 비즈니스 로직을 분리하기 위해 사용

* **DTO(Data Transfer Object)**
  
  * 계층 간 데이터 교환을 하기 위해 사용되는 객체
  
  * 로직을 가지지 않는 순수한 데이터 객체 (Getter 및 Setter만 가짐)
  
  현재 프로그램에 적용된 DTO
  
  : 유저가 브라우저에 데이터를 입력하여 Form에 있는 데이터를 DTO에 넣어서 전송. 해당 DTO를 받은 서버가 DAO를 이용하여 데이터베이스로 데이터를 집어넣음.

* **VO(Value Object)**
  
  * 값을 읽기 위해 사용.
  
  * Read-Only인 점이 DTO와 다름  

<br></br>

* 왜 DTO를 사용할까?
  
  Controller가 클라이언트의 요청에 대한 응답으로 도메인 Model를 직접적으로 넘겨주면,
  
  * 불필요한 데이터까지 넘겨주게 된다.
  
  * UI계층에서 Model의 상태를 변경시킬 위험이 존재한다.
  
  Entity 클래스를 Controller의 Reqeust / Response 클래스로 사용하게 된다면,
  
  * Entity 클래스를 기준으로 테이블이 생성이 되는데, 이 클래스를 변경하게 되면 너무 큰 변경이 이루어진다.  


<br></br>
* 본 프로젝트에서...
  
  * 회원가입을 위해 생성시킨 MemberSignupRequestDTO 말고는 다 직접 Entity를 사용하고 있었다.
  
  * 그마저도 MemberSignupRequestDTO는 비밀번호를 포함하고 있어서 view에 member의 정보를 노출시키기 위한 DTO로 적절하지 않다고 생각한다.
  
  * 각 엔티티와 view에 의해 노출되는/수정되는 data를 적절히 고민해서,, Book, Comment에 대한 DTO도 생성시켜야 할 것 같다.
  
  * DTO에서 Entity로 변경 시 Builder() 패턴이 사용되는데 이는 또 따로 정리해봐야할 것 같다!
