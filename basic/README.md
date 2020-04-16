# OAuth 2.0

## 기본적으로 셋팅한 값
### client

ID/Secret

`clientId` / `secret`

### 계정
id/pw

`joohyung0531@gmail.com` / `pass`

### 사용자측 정보
사용하는 쪽은 `localhost:9000`에서 `oauth2.0`에 맞춰 처리한다고 가정함

## 사용법

### 1. 아래 링크로 접근 후, 인증(로그인) 및 scope 제공 동의
```
http://localhost:8081/oauth/authorize?response_type=code&client_id=clientId&redirect_uri=http://localhost:9000/callback&scope=read_profile
```

### 2. code -> token 교환
받은 code값으로 token값을 교환한다.

```
$ curl -i -u clientId:secret \
-d "grant_type=authorization_code&code=(받은 code 값)&redirect_uri=http://localhost:9000/callback" \
-X POST http://localhost:8081/oauth/token
```

### 3. access token으로 리소스 사용 - access token 인증 받은 사용자 정보 얻기

```
$ curl 'http://localhost:8080/me' -H 'authorization: Bearer 발급받은_access_token'
```


## resource server 토큰 인증
리소스 서버에서 token 인증 방법은 2가지인데 (jwt 사용 안한다고 가정) 

1. `authentication 서버`로 access token 값을 담아, 요청을 날려 검증하는 방법(`/oauth/check_token`)
2. 자체적으로 resource 서버에서 db에서 값을 찾아서 검증하는 방법(db 정보를 알고 있을 시 유용)

이 중 첫번째 방법을 사용. 소스 상에서 `token store`를 구현하면 따로 검증 리퀘스트를 요청하지 않고 알아서 2번째 방법으로 검증한다.

## TODO
1. DB 스키마 정리 (PK등)