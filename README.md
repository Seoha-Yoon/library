# 도서관 웹 서비스 프로젝트

## 개발일지

[Day1. 단순 로그인, 회원가입 구현](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day1.md)  
[Day2. Spring Security 이용 로그인, 회원가입 구현](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day2.md)  
[Day3. 도서 대여 서비스 구현 (작성중)](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day3.md)  
[Day4. 댓글 CRUD (작성중)](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day4.md)  
[Day5. MySQL 설치 및 연동](https://github.com/Seoha-Yoon/library/blob/main/개발일지/도서관_day5.md)  
[DTO와 DAO](https://github.com/Seoha-Yoon/library/blob/main/개발일지/DAO_DTO.md)

## 구현 기능

#### 1. 회원가입

- 이메일, 비밀번호 회원가입
- 이메일 중복 불가

#### 2. 로그인

- 이메일, 비밀번호 로그인
- 로그아웃
- 역할에 따라 페이지 접근 권한 관리 (User, Admin)
- 닉네임 수정시에도 책 대여 목록, 댓글 유지

#### 3. 책 대여/반납

- 책의 stock에 따라 책 status 변경 (AVAILABLE, UNAVAILABLE)
- AVAILABLE status 가진 책만 대여하기 버튼 있음.
- 대여 시 책 stock 감소
- 반납 시 책 stock 증가
- 사용자 선택 X, 현재 로그인 된 사용자 확인해서 사용

#### 4. 댓글

- 각 책마다 댓글 등록
- 등록 시 등록 시간 저장
- 업데이트 시 수정 시간 저장

#### 5. 마이페이지

- 본인이 대여한 책 목록 확인
- 책 반납 가능, 반납 시 목록에서 삭제
- 회원 정보 수정

## 추가 / 수정할 기능

#### 1. 회원가입

- 회원가입 시 메일 인증
- 닉네임 중복 불가
- 회원 탈퇴 시 댓글 삭제, 대여 책 원복

#### 2. 로그인

- 소셜로그인

#### 3. 책 대여/반납

- 여러 개 선택해서 한 번에 대여 가능 (최대 3권) 체크박스로 대여하기?
- 책 목록에서 책 이미지도 확인 가능하게

#### 4. 댓글

- 댓글 공감 기능
- 댓글 공감 수 표시
- 본인이 작성한 댓글 삭제

#### 5. 마이페이지

- 본인이 쓴 댓글 확인
