# REST API

## REST API 설계

1. URI는 정보의 자원을 표현해야 한다.

2. 자원에 대한 행위는 HTTP Method (GET, POST, PUT, DELETE)로 표현한다.



기존의 URI를 RESTful하게 바꿨다.

**책 조회**

*기존 URI*

```
GET /books/{bookId}/detail
```

*수정된 URI*

```
GET /books/{bookId}
```

**책 삭제**

*기존 URI*

```
GET /books/{bookId}/return
```

*수정된 URI*

```
DELETE /books/{bookId}
```
