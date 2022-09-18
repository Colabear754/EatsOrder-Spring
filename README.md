# EatsOrder-Spring
Spring으로 변환한 배달 프로젝트

### 기본 준비사항
DB는 Oracle DB를 사용하며, 기본으로 설정된 계정과 암호는 다음과 같으며, 프로시저 생성 권한과 뷰 생성 권한이 필요합니다.   
`DB계정 : eatsorder`   
`DB암호 : 1234`   

만약 임의의 DB계정과 암호를 사용하고 싶다면, /src/main/webapp/WEB-INF/spring/root-context.xml에서 username과 password의 value를 수정해주세요.
```xml
<bean class="org.springframework.jdbc.datasource.DriverManagerDataSource" id="dataSource">
  <!-- <property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" /> -->
  <!-- <property name="url" value="jdbc:oracle:thin:@localhost:1521:orcl" /> -->
  <property name="driverClassName" value="net.sf.log4jdbc.sql.jdbcapi.DriverSpy" />
  <property name="url" value="jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl" />
  <property name="username" value="eatsorder" />
  <property name="password" value="1234" />
</bean>
```

프로젝트를 실행하기 위해서는 DB의 sys계정으로 로그인하여 아래의 SQL 구문을 실행하여 프로젝트 계정에 권한을 부여해야 합니다.   
```SQL
grant execute on DBMS_CRYPTO to (프로젝트계정명);
```

계정이 준비되었으면 data.sql 파일의 SQL 구문을 실행하여 DB모델과 예제 데이터를 삽입합니다.
