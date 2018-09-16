<script type="text/javascript">
    function onBridgeReady(){
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId":"{payConfigDTO.appid}",     //公众号名称，由商户传入
                "timeStamp":"{payConfigDTO.timeStamp}",         //时间戳，自1970年以来的秒数
                "nonceStr":"{payConfigDTO.nonce_str}", //随机串
                "package":"{payConfigDTO.prepay_id}",
                "signType":"{payConfigDTO.sign_Type}",         //微信签名方式：
                "paySign":"{payConfigDTO.sign}" //微信签名
            },
            function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok" ){
                    // 使用以上方式判断前端返回,微信团队郑重提示：
                    //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                }
            });
    }
    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>