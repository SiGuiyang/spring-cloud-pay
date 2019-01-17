## spring-cloud-pay
   spring-cloud 架构方案支付，调用逻辑已实现，支付功能未实现，此项目不提供支付功能，只提供多支付平台设计方案脚手架，
   具体支付功能小伙伴们可以自行添加设计即可。

## 架构方案
![支付方案](https://github.com/SiGuiyang/spring-cloud-pay/blob/master/pay.png)
## 设计思路
1. 逐步启动pay-server，pay-config（可选择添加配置中心），pay-zuul，pay-app，pay-channel，pay-alipay，pay-weixin服务。
2. 所有请求对外请求都是通过pay-zuul网关转发到pay-app支付中心过来，内部请求不走网关服务。
3. pay-app根据支付平台分配的商户号，走不同的支付渠道，根据支付渠道调用不同的支付服务
4. 通过pay-server 注册中心获取负载均衡地址，发送真实支付请求服务。

##### 注意： 若新增其它第三方的支付服务，水平扩展新增支付服务的项目，并注册到pay-server，在pay-channel 修改相应选择支付渠道即可

#### 欢迎各方大佬点评，如有不足，请多多包涵
