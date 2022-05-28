# jpashop

## h2 database

bin/h2.sh 실행

실행 후 localhost:8082 

JDBC URL :`jdbc:h2:~/jpashop`

JDBC URL TCP : `jdbc:h2:tcp://localhost/~/jpashop`

## test 만들기 단축키
cmd + shift + t


## build

아래 명령 실행 시 build/libs 에 산출물 생성됨

```bash
./gradlew clean build
```

## fetch

ManyToOne 은 기본값이 `EAGER` 이므로 `LAZY` 로 변경 필요.

실무에서는 반드시 `LAZY` 사용. 


