# 도서관 웹 서비스 프로젝트

## Day2. Spring Security를 이용한 회원가입, 로그인 구현

#### 페이지 설계

1) **로그인 페이지 "/auth/login"**
   
   * 로그인을 할 수 있는 페이지
   
   * 누구나 접근 가능
2. **회원가입 페이지 "/auth/signUp"**
   
   * 회원가입을 할 수 있는 페이지
   
   * Form을 이용하여 (이름, 이메일, 패스워드)와 같은 정보를 받음
   
   * Admin/User 권한 설정 토이프로젝트이므로 권한 선택 기능을 추가함

3. **유저 페이지 "/dashboard"**
   
   * 로그인을 성공했을 때 넘어오는 페이지
   
   * 유저 및 어드민만 접근 가능

4. **관리자 페이지 "/admin"**
   
   * 관리자만 접근 가능한 페이지
   
   * 회원 목록 조회





#### Spring Security 로그인 구현

**1. Config 파일 생성**

* WebSecurityConfigureAdapter 클래스를 상속받는 Config 클래스 작성
  
  * @EnableWebSecurity, @Configuuration

* 인증을 무시할 경로, http 관련 인증 등을 설정한다.
  
  * **configure(WebSecurity web)**: 인증을 무시할 경로 ex) css, js, h2
  
  * **configure(HttpSecurity http)**: http 관련 인증 설정
    
    * http.authorizeRequests().antMatchers(해당 url).권한 설정
      
      * permitAll(), hasRole(), anyRequest(), authenticated()
    
    * http.csrf().disable(): h2-console 사용하기 위해 설정
    
    * .formLogin().loginPage(*url*): login 페이지를 설정하지 않아도 '/login'으로 로그인창 default이지만 커스텀 로그인창을 설정하기 위해 사용
    
    * .logout(): 아직 구현하지 않음

* @Bean BCryptPasswordEncoder endcodePassword()
  
  : 회원가입 시 비밀번호 암호화에 사용

**2. UserDetailsImpl.java 구현**

* UserDetails를 상속받아 구현. 메서드 오버라이딩 하여 구현

* 이때, getAuthorities()는 사용자의 권한 목록을 Collection 형태로 반환한다.

**3. UserServiceImpl.java 구현**

* UserDetailService를 상속받아 구현한다.

* 이때, 오버라이딩한 loadUserByUserName 함수가 등록되지 않은 사용자를 구분하는 예외처리를 해준다.







#### 회원가입 구현

* 회원가입 시 **Member** 클래스가 아닌 **MemberSignUpRequestDto**라는 클래스를 만들어서 회원가입을 진행했다.

* DTO(Data Transfer Object)
  
  * DB에서 데이터를 얻어 서비스나 컨트롤러 등으로 보낼 때 사용
  
  * 로직을 갖고 있지 않은 순수한 데이터 객체 (getter와 setter만 가짐)

* Form에서 데이터를 받아 DTO 객체를 생성한다. DTO 객체를 이용하여 회원가입을 시켜준다.
  
  * Member 객체를 생성한다.
  
  * Member 객체의 비밀번호를 암호화한다.
  
  * Member 객체를 DB에 저장한다.

* h2 db에 비밀번호가 암호화되어 유저정보가 저장된 것을 확인할 수 있다.

* 회원 가입시, select 박스를 이용하여 Admin/User 권한을 직접 설정할 수 있도록 해주었다.

!로그인 및 회원가입 시 사용되는 MemberEntity, MemberRepository, MemberService 등은 이전에 구현해놨던 것으로 사용했다.







#### 구현 결과

* USER의 권한을 가진 사용자는 '/dashboard' 페이지에 접근 가능하고, ADMIN의 권한을 가진 사용자는 '/admin' 페이지에서 회원 목록을 확인할 수 있었다.

* 이 때, USER의 권한을 가진 사용자는 '/admin' 페이지에 접근하지 못하는 것을 확인할 수 있다.

* 로그인 및 회원가입 페이지 외 다른 페이지에 접근하려면 자동으로 로그인 페이지로 이동되는 것을 확인할 수 있다.







#### 추가할 점 및 수정할 점

* 권한 핸들링을 제대로 하지 못했다. db에 ROLE_ADMIN, ROLE_USER와 같이 저장되어야 하는데 USER, ADMIN으로 글자만 저장되고 있다.

* 로그인 시 한 번씩 튕기는 현상이 발생한다. 수정 해야할 듯.
  
  ex) 회원가입 후 로그인 진행 -> succesful 페이지가 아닌 '/login'페이지로 가서 페이지 에러가 남

* USER가 접근 가능한 페이지에 이제 도서목록 CRUD를 구현해보자!
