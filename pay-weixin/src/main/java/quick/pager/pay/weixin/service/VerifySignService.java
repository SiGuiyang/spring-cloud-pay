package quick.pager.pay.weixin.service;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.pay.WeiXinSubmitPayDto;
import quick.pager.pay.mapper.merchant.MerchantMapper;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.pay.WeiXinSignVO;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 验证签名服务
 */
@Service
@Slf4j
public class VerifySignService implements IService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Response doService(BaseDto dto) {
        WeiXinSubmitPayDto weiXinSubmitPayDto = (WeiXinSubmitPayDto) dto;
        log.info("商户开始验签 merchantOrderNo = {}", weiXinSubmitPayDto.getMerchantOrderNo());

        Response<WeiXinSignVO> response = new Response<>();

        SortedMap<String, String> postMap = new TreeMap<>();
        postMap.put("merchantOrderNo", weiXinSubmitPayDto.getMerchantOrderNo());
        postMap.put("notifyUrl", weiXinSubmitPayDto.getNotifyUrl());
        postMap.put("timestamp", weiXinSubmitPayDto.getTimestamp());
        postMap.put("body", weiXinSubmitPayDto.getBody());
        postMap.put("amount", weiXinSubmitPayDto.getAmount().toString());
        postMap.put("signType", weiXinSubmitPayDto.getSignType());
        StringBuilder builder = new StringBuilder();
        postMap.forEach((k, v) -> builder.append(k).append("=").append(v).append("&"));

        Merchant merchant = merchantMapper.selectMerchantByMerchantNo(weiXinSubmitPayDto.getMerchantOrderNo());

        String sign = getSign(merchant.getPrivateKey(), builder, weiXinSubmitPayDto.getSignType());
        // 验证签名
        if (sign.equals(weiXinSubmitPayDto.getSign())) {
            log.info("验证签名成功 merchantOrderNo = {}", weiXinSubmitPayDto.getMerchantOrderNo());
            WeiXinSignVO vo = new WeiXinSignVO();
            vo.setAmount(weiXinSubmitPayDto.getAmount());
            vo.setBody(weiXinSubmitPayDto.getBody());
            vo.setMerchantOrderNo(weiXinSubmitPayDto.getMerchantOrderNo());
            vo.setNotifyUrl(weiXinSubmitPayDto.getNotifyUrl());

            response.setData(vo);

        } else {
            response.setCode(ResponseStatus.UNKNOWN_SIGN.code);
            response.setMsg(ResponseStatus.UNKNOWN_SIGN.msg);
        }

        return response;
    }

    /**
     * 获取签名
     *
     * @param privateKey 商户私钥
     * @param builder    支付入参
     */
    private String getSign(String privateKey, StringBuilder builder, String signType) {
        log.info("当前的签名方式为 signType = {}", signType);
        StringBuilder sb = new StringBuilder();
        // MD5加密方式
        if (Constants.SignType.MD5.name().equals(signType)) {
            String result = sb.append("key").append("=")
                    .append(builder.toString().substring(0, builder.toString().lastIndexOf("&"))).toString();
            log.info("最后组装成要签名的字符串 result= {}", result);
            String md5 = SecureUtil.md5(result);
            log.info("生成的MD5签名 md5= {}", md5);

            return md5;
        } else {
            return "";
        }
    }

//    public static void main(String[] args) {
//        SortedMap<String, String> postMap = new TreeMap<>();
//        postMap.put("appid", "wxd930ea5d5a258f4f");
//        postMap.put("mch_id", "10000100");
//        postMap.put("device_info", "1000");
//        postMap.put("body", "test");
//        postMap.put("nonce_str", "ibuaiVcKdpRxkhJA");
//        StringBuilder builder = new StringBuilder();
//        postMap.forEach((k, v) -> {
//            builder.append(k).append("=").append(v).append("&");
//        });
//
//        String value = builder.toString().substring(0, builder.toString().lastIndexOf("&"));
//
//        String result = SecureUtil.md5(value + "&key=192006250b4c09247ec02edce69f6a2d").toUpperCase();
//        System.out.println(result.equals("9A0A8659F005D6984697E2CA0A9CF3B7"));
//    }
}
