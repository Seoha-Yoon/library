# 도서관 웹 서비스 프로젝트

### 개발일지
[Day1. 단순 로그인, 회원가입 구현](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day1.md)  
[Day2. Spring Security 이용 로그인, 회원가입 구현](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day2.md)  
[Day3. 도서 대여 서비스 구현 (작성중)](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day3.md)  
[Day4. 댓글 CRUD (작성중)](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day4.md)  
[Day5. MySQL 설치 및 연동](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day5.md)  


### 구현기능
#### 1. 회원가입
- 이메일, 비밀번호 회원가입
- 이메일 중복 불가

#### 2. 로그인
- 이메일, 비밀번호 로그인
- 로그아웃
- 역할에 따라 페이지 접근 권한 관리 (User, Admin)

#### 3. 책 대여/반납
- 책 대여/반납 시 책 status 변경 (AVAILABLE, USED)

#### 4. 댓글
- 각 책마다 댓글 등록
- 등록 시 등록 시간 저장
- 업데이트 시 수정 시간 저장

#### 5. 마이페이지
- 본인이 대여한 책 목록 확인
- 책 반납 가능, 반납 시 목록에서 삭제
- 회원 정보 수정
