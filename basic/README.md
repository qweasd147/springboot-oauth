# OAuth 2.0

```
http://localhost:8081/oauth/authorize?response_type=code&client_id=clientId&redirect_uri=http://localhost:9000/callback&scope=read_profile
```

## 기본적으로 셋팅한 값
### client
* client id/client secret
* clientId/secret

### 계정
id/pw
* joohyung0531@gmail.com/pass

## resource server 토큰 인증
리소스 서버에서 token 인증 방법은 2가지인데 (jwt 사용 안한다고 가정) 

1. `authentication 서버`로 access token 값을 담아, 요청을 날려 검증하는 방법(`/oauth/check_token`)
2. 자체적으로 resource 서버에서 db에서 값을 찾아서 검증하는 방법(db 정보를 알고 있을 시 유용)

이 중 첫번째 방법을 사용. 소스 상에서 `token store`를 구현하면 따로 검증 리퀘스트를 요청하지 않고 알아서 2번째 방법으로 검증한다.