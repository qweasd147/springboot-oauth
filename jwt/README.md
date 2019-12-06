# OAuth with JWT

## 설치 방법

`auth-server` port 8080
`auth-server` port 8081

### 개발 환경
`mysql`(port 3306),`auth-server`, `resources-server` 프로젝트를 각각 올린다.

### 배포 환경
docker image들을 실행한다.
```
$ docker-compose up -d
```

## 테스트

### 회원 가입하기
```
$ curl -X POST \
-F 'email=joohyung05315@gmail.com' \
-F 'name=joo' \
-F 'nickName=nickname421421' \
-F 'password=joo123' http://localhost:8080/users/signup
```

### 토큰 만들기(내부에서 로그인 처리)
```
$ curl -i -u clientId:secret \
-d "grant_type=password&username=(받은 idx 값)&password=joo123" \
-X POST http://localhost:8080/oauth/token
```

### 로그인 정보 확인 by token
```
$ curl 'http://localhost:8081/me' -H 'authorization: Bearer 발급받은 access token'
```

## 옵션