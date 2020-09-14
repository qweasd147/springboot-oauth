# OAuth with JWT

## Resource Owner Password Credentials Grant (password) 방식

## 설치 방법

`auth-server` port 8081
`resource-server` port 8080

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
-F 'password=joo123' http://localhost:8081/users/signup
```

### 토큰 만들기(내부에서 로그인 처리)

```
$ curl -i -u clientId:secret \
-d "grant_type=password&username=(받은 idx 값)&password=joo123" \
-X POST http://localhost:8081/oauth/token
```

### 로그인 정보 확인 by token

```
$ curl 'http://localhost:8080/me' -H 'authorization: Bearer 발급받은 access token'
```

### access token 토큰 재발급(refresh token)

```
$ curl -i -u "clientId:secret" \
-d "grant_type=refresh_token&refresh_token=(발급 받은 refresh token)" \
-X POST http://localhost:8081/oauth/token
```

## 옵션

### 토큰 정보

서비스 가능한 토큰의 종류 및 옵션은 `oauth_client_details` 테이블에서 관리한다.

테이블 주요 옵션

- client_id, client_secret 서버단에서 관리하고 어플리케이션 구분하는 값(`PK`)이 된다.
- authrized_grant_types 해당 어플리케이션에 서비스 해 줄 토큰 타입
- access_token_validity access token 유효기간(단위 초)
- refresh_token_validity refresh token 유효기간(단위 초)

## docker hub image 정보

auth server
`https://hub.docker.com/repository/docker/qweasd147/auth-server`

resource server
`https://hub.docker.com/repository/docker/qweasd147/resource-server`

빌드 시키기도 귀찮으면 `docker-compose.yml`파일에 `auth.build`, `res.build`를 날려버리고 각각
이미지 정보(`qweasd147/auth-server:1.0.0`, `qweasd147/resource-server:1.0.0`)를 넣어도 된다.

## TODO

docker image layer 캐싱 적용 중, 각 프로젝트의 app build, docker build & push를 한번에 하려고 작업중인데
shell script가 아직 미숙해서 한번에 적용이 안되고 있음.

현재는 각 프로젝트로 이동 후, `sh docker-build-push.sh {태그 번호}`를 해야 하는데 테스트&빌드를 어떻게 처리해야하는지 고민중

## JKS 적용

`Sign key`를 지정해서 `Auth 서버`와 `Resource 서버`에 넣어놓고 사용해도 괜찮긴 하지만 여러 `Resource 서버`를 만들어 놓고 사용하다가 노출되면 보안사고가 날 수도 있다.
차라리 공개키 방식으로 셋팅해 놓으면 `Auth 서버`의 개인키만 잘 관리하고 공개키는 여러군데 걸쳐 사용해도 괜찮다. 하지만 인증서는 유효기간이 있으므로 이것도 꼭 고려해야한다.

key 파일들은 임의로 만들어 셋팅해 놧지만 진짜로 다른곳에서 서비스 할려면 key 파일만 만들어서 바꿔놓아야 한다. 밑에껀 현재 셋팅되어 있는 JKS 파일 생성 옵션 & 명령어 들이다.

### key 파일 생성

유효기간이 100년(...)인 인증서 생성

```sh
$ keytool -genkeypair -alias oauth2jwt -keyalg RSA -validity 36500 \
-keypass dev123 -keystore oauth2jwt.jks -storepass dev321

# 마지막에 y
```

### 공개키 확인

```sh
$ keytool -list -rfc --keystore oauth2jwt.jks | openssl x509 -inform pem -pubkey

# 키 저장소 비밀번호 입력: dev321
```

이때 보여지는

```
-----BEGIN PUBLIC KEY-----
~~~~
~~~~
~~~~
-----END PUBLIC KEY-----
```

전부가 다 공개키 이다.

### 인증서 정보 보기(유효기간 포함)

```sh
$ keytool -list -v -keystore oauth2jwt.jks

# 키 저장소 비밀번호 입력: dev321
```
