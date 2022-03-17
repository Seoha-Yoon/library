# 도서관 웹 서비스 프로젝트

## Day3. 도서 대여 서비스 구현

* 도서 csv 파일을 h2 database에 import 하여 사용했다.

```sql
create table book as select * from csvread('경로/book_data.csv');
```

* 데이터를 계속 임포트 하는 것이 귀찮아 application.yml ddl-auto 설정값을 create에서 update로 바꿔주었다.
  
  * **create**: 해당하는 테이블이 있으면 Drop 후 다시 생성
  
  * **create-drop**: 종료 시점에 테이블 Drop 
  
  * **update**: 변경분만 반영
  
  * **validate**: 엔티티와 테이블이 정상 매핑되었는지만 확인
  
  * **none**: 사용하지 않음

* 처음에 Book Entity를 만들어 놓은 후 data를 import 했더니, column 위치가 맞지 않아 import 되지 않는 문제가 생겼다. 그래서 table을 삭제하고 다시 import 시켜줬다.

```sql
drop table if exists book CASCADE;
```

=> 추후 csv 파일을 직접 파싱해서 import 하는 방법도 해 볼 예정이다. (Spring Batch)  



*JPA를 사용하여 구현했다. 따로 JPA를 더 깊게 공부해봐야 할 것 같다.*

#### 1. 도서 대여 서비스 구현 (진행중)

1.  필요한 엔티티 생성 및 설계
* **엔티티 간 연관관계**
  
  (연관관계 이미지 추가 예정)
  
  * Book: 도서 엔티티
  
  * Member: 회원 엔티티
  
  * BookStatus: 도서의 대여 가능 여부 알려주는 enum
  
  * Rent: 대여 엔티티
  
  * RentBook: Rent와 Book 사이의 다대다 관계를 풀어주는 연결 엔티티 

   

2. 각 엔티티 간  연관관계 매핑 및 연관관계 함수 구현
* 위에서 설계한 연관관계에 따라 각 엔티티에 연관관계를 구현해준다.
  
  * OneToOne
  
  * OneToMany
  
  * ManyToOne
  
  * ManyToMany
  
  
3. 비즈니스 로직 구현 (ex 상태 변경)

4. Repository, Service 구현

5. Controller 구현



#### 2. 도서 목록 조회

현대 도서관에 있는 도서 목록을 조회해서 보여주는 페이지를 만들어주었다.

findAll 함수를 이용하여 return된 Book의 List 값을 thymleaf를 이용하여 구현해주었다.

**BookController.java**

```java
@GetMapping("/books")
public String list(Model model) {
    List<Book> books = bookService.findBooks();
    model.addAttribute("books",books);
    return "library/bookList";
}
```

**BookRepository.java**

```java
public List<Book> findAll(){
    return em.createQuery("select b from Book b", Book.class).getResultList();
}
```

**library/bookList.html**

```html
<tr th:each="book : ${books}">
    <td th:text="${book.id}"></td>
    <td th:text="${book.title}"></td>
    <td th:text="${book.author}"></td>
</tr>
```
