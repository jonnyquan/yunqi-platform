
1.环境配置
dev：开发环境
tp（test for performance）：性能测试环境
tf（test for function）：功能测试环境
pp（pre for publish）：预发布环境
pd（product）：生产环境


获取token
http://192.168.1.152:50010/user/token
{
	"accountId":"admin",
	"password":"123456"
}

测试异步接口
http://192.168.1.152:50010/user/testasyn/test1?accessToken=yunqi%3Atoken%3Af6a76c6226e540beaa44d8181e02d7ca
{}