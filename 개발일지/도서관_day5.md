# 도서관 웹 서비스 프로젝트

## Day5. MySQL 설치 및 연동

### 1. MySQL 설치

homebrew를 이용하여 설치

```shell
% brew install mysql
```

다음 명령어만 입력하면 sql 서버가 정상적으로 실행된다.

```shell
% mysql.server start
```

위 방법처럼 brew로 설치만 하면 실행되는 간단한 방법인데, 온갖 에러들 때문에 몇시간동안 고생했다...  

전에 설치해보겠다고 깔짝 거리던 때가 있었는데, 아마 그때 뭔가 설정파일이 잘못된 거 아닐까 싶다.  

그래서 깔-끔 하게 다 지우고 시작하는 법을 택했다!  

* [Remove MySQL completely from Mac OSX · GitHub](https://gist.github.com/vitorbritto/0555879fe4414d18569d)  



### 2. MySQL ROOT 비밀번호 설정

**mysql_secure_installation** 명령어로 MySQL ROOT 비밀번호를 설정할 수 있다.

1. Would you like to setup VALIDATE PASSWORD component? (비밀번호 가이드 설정에 대한 질문)  
   yes: 복잡한 비밀번호  
   no: 쉬운 비밀번호  

2. Remove anonymous users? (사용자 설정에 관한 질문)  
   yes: 접속시 -u 옵션필요  
   no: 접속시 -u 옵션 불필요 

3. Disallow root login remotely? (다른 IP에서 root 아이디로 원격접속을 설정)  
   yes: 원격접속 불가능  
   no: 원격접속 가능  

4. Remove test database and access to it? (테스트 데이터베이스 설정)  
   yes: 테스트 데이터베이스 제거  
   no: 테스트 데이터베이스 유지  

5. Reload privilege tables now? (변경된 권한을 테이블에 적용)  
   yes: 적용  
   no: 미적용

mysql 서버에 이제 비밀번호를 사용하여 접속할 수 있다.   

```shell
% mysql -uroot -p
```

### 3. Sequel Pro와 연결

SQL 클라이언트를 설치해주었다. 앞에서 설정한 root 비밀번호를 입력하여 연결을 하려는데.. 또 에러가 발생했다.

<img title="" src="https://user-images.githubusercontent.com/60412023/159520704-4ef9a82e-56f5-4f78-912c-6686c6e8a6ef.png" alt="스크린샷 2022-03-23 오전 12.16.41.png" width="387" data-align="center">

<img title="" src="https://user-images.githubusercontent.com/60412023/159520717-47c428e8-59fa-40a5-a54e-e92a4cac19c4.png" alt="스크린샷 2022-03-23 오전 12.17.27.png" width="426" data-align="center">

비밀번호 문제인데.. 해결방법은 밑에 나와있다.

[Sequel Pro and MySQL connection failed - Stack Overflow](https://stackoverflow.com/questions/51179516/sequel-pro-and-mysql-connection-failed)

* my.cnf 파일에 가서 [mysqld] 섹션에 다음 line 추가
  
  * 이 때, my.cnf 파일은 /usr/local/etc/my.cnf에 있다. vi 명령어로 수정한다.
  
  ```
  default-authentication-plugin=mysql_native_password
  ```

* mysql server terminal에 다음 command 작성
  
  ```
  ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '[password]';
  ```

* mysql shell을 나간 뒤 brew services restart mysql 후 재접속.

연결이 성공적으로 되는 것을 확인할 수 있다!



### 4. Spring Project와 연동

* Build.gradle 수정

```java
// runtimeOnly 'com.h2database:h2'
runtimeOnly 'mysql:mysql-connector-java'
```

* application.yaml 파일 수정

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/[db이름]?serverTimezone=UTC&characterEncoding=UTF-8
    username: root
    password:
    driver-class-name: com.mysql.cj.jdbc.Driver
```

-> 위 설정이 다 끝나면 보통 연결이 되어야하는데.. 오류가 나서 추가적으로 접속 체크를 해줬다.

-> 처음에 [db이름]을 가진 DB가 없어!! 에러를 뱉었는데, 그냥 철자오류였다. library를 libarary로 써서..ㅎㅎ



* MySQL 접속 체크
  
  * IntelliJ 오른쪽 부분의 Database 클릭
  
  * '+'  -> 'DataSource' -> MySQL
  
  * Test Connection 성공 시키기

위와 같은 과정을 거치면 정상적으로 DB에 연동되는 것을 확인할 수 있다.



### 5. 결과 화면

<img width="938" alt="스크린샷 2022-03-22 오후 11 24 42" src="https://user-images.githubusercontent.com/60412023/159520296-4345a62f-0391-47f8-a58c-0ad6f96af663.png">

수많은 에러를 뱉어낸 mysql과의 싸움일지 끝~
