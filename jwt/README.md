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

### access token 토큰 재발급(refresh token)

```
$ curl -i -u "clientId:secret" \
-d "grant_type=refresh_token&refresh_token=(발급 받은 refresh token)" \
-X POST http://localhost:8080/oauth/token
```

## 옵션

### 토큰 정보
서비스 가능한 토큰의 종류 및 옵션은 `oauth_client_details` 테이블에서 관리한다.

테이블 주요 옵션
* client_id, client_secret 서버단에서 관리하고 어플리케이션 구분하는 값(`PK`)이 된다.
* authrized_grant_types 해당 어플리케이션에 서비스 해 줄 토큰 타입
* access_token_validity access token 유효기간(단위 초)
* refresh_token_validity refresh token 유효기간(단위 초)

## docker hub image 정보

auth server
`https://hub.docker.com/repository/docker/qweasd147/auth-server`

resource server
`https://hub.docker.com/repository/docker/qweasd147/resource-server`

빌드 시키기도 귀찮으면 `docker-compose.yml`파일에 `auth.build`, `res.build`를 날려버리고 각각
이미지 정보(`qweasd147/auth-server:1.0.0`, `qweasd147/resource-server:1.0.0`)를 넣어도 된다.