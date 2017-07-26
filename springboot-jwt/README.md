## JWT
- JWT是 Json Web Token 的缩写。它是基于 RFC 7519 标准定义的一种可以安全传输的 小巧 和 自包含 的JSON对象。由于数据是使用数字签名的，所以是可信任的和安全的。JWT可以使用HMAC算法对secret进行加密或者使用RSA的公钥私钥对来进行签名。

- 用户发送按照约定，向服务端发送 Header、Payload 和 Signature，并包含认证信息（密码），验证通过后服务端返回一个token,之后用户使用该token作为登录凭证，适合于移动端和api

- 具体流程如下：
  1.登陆时生成token,并将token 保存到本地(提供两种方案：使用cookie 或者 localStorage)
  2.在需要权限控制的请求中都带着token
  3.验证token是否失效,如果失效,提示实现或者重定向到登陆页面,重新获取token.
- [获取Token](http://127.0.0.1:8001/oauth/token)
* post man 用Post请求
```json
{
	
	"clientId":"1",
	"username":"test",
	"password":"123456"
}
```
- [API请求地址](http://127.0.0.1:8001/oauth/user)  
