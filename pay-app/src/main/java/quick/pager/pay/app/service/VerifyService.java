package quick.pager.pay.app.service;

import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.app.mapper.MerchantMapper;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDTO;
import quick.pager.pay.app.dto.SubmitPayDTO;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 验证签名
 *
 * @author siguiyang
 */
@Service
@Slf4j
public class VerifyService implements IService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Response doService(BaseDTO dto) {

        SubmitPayDTO submit = (SubmitPayDTO) dto;

        Response<SubmitPayDTO> response = new Response<>();

        SortedMap<String, String> postMap = new TreeMap<>();

        postMap.put("merchantNo", submit.getMerchantNo());
        postMap.put("merchantOrderCode", submit.getMerchantOrderCode());
        postMap.put("notifyUrl", submit.getNotifyUrl());
        postMap.put("clientIp", submit.getClientIp());
        postMap.put("payAmount", submit.getPayAmount().toString());
        postMap.put("payType", submit.getPayType());
        postMap.put("bankCardNum", submit.getBankCardNum());
        postMap.put("bankCode", submit.getBankCode());
        postMap.put("body", URLUtil.decode(submit.getBody()));
        postMap.put("timestamp", submit.getTimestamp());
        postMap.put("signType", submit.getSignType());

        String join = Joiner.on("&").withKeyValueSeparator("=").join(postMap);

        Merchant merchant = merchantMapper.selectMerchantByMerchantNo(submit.getMerchantNo());

        Response verifyMerchantResponse = verifyMerchant(merchant);
        // 验证不成功
        if (ResponseStatus.SUCCESS.code != verifyMerchantResponse.getCode()) {
            return verifyMerchantResponse;
        }

        String originSign = getSign(new StringBuilder(join), merchant.getPrivateKey(), submit.getSignType());

        if (!originSign.equals(submit.getSign())) {
            log.warn("商户签名与平台签名不匹配 sign = {},originSign = {}", submit.getSign(), originSign);
            response.setCode(ResponseStatus.PAY_SIGN_NOT_CORRECT.code);
            response.setMsg(ResponseStatus.PAY_SIGN_NOT_CORRECT.msg);
            return response;

        }

        response.setData(submit);

        return response;
    }

    /**
     * 验证商户账号的合法性
     *
     * @return 商户账号合法code = 200，反之其它业务code码
     */
    private Response verifyMerchant(Merchant merchant) {
        // 判断商户是否存在
        if (ObjectUtils.isEmpty(merchant)) {
            return Response.builder().code(ResponseStatus.UNKNOWN_PAY_MERCHANT_NO.code).msg(ResponseStatus.UNKNOWN_PAY_MERCHANT_ORDER_CODE.msg).build();
        }

        Response response = new Response();

        switch (merchant.getServerStatus()) {
            case 0:
                response = Response.builder().code(ResponseStatus.MERCHANT_REVIEW.code).msg(ResponseStatus.MERCHANT_REVIEW.msg).build();
                break;
            case 1:
                response = Response.builder().code(ResponseStatus.MERCHANT_ACCOUNT_CANCELLATION.code).msg(ResponseStatus.MERCHANT_ACCOUNT_CANCELLATION.msg).build();
                break;
            case 3:
                response = Response.builder().code(ResponseStatus.MERCHANT_REVIEW_FAILURE.code).msg(ResponseStatus.MERCHANT_REVIEW_FAILURE.msg).build();
                break;
        }

        return response;

    }

    /**
     * 获取签名
     *
     * @param builder    builder
     * @param privateKey 商户签名密钥
     * @param signType   签名方法
     */
    private String getSign(StringBuilder builder, String privateKey, String signType) {
        String origin = builder.append("&").append("key").append("=").append(privateKey).toString();
        String result = "";
        if (Constants.SignType.MD5.name().equals(signType)) {
            result = SecureUtil.md5(origin);

        } else if (Constants.SignType.AES.name().equals(signType)){

        } else if (Constants.SignType.HmacSHA256.name().equals(signType)){

        } else if (Constants.SignType.RSA2.name().equals(signType)) {

        } else {

        }

        log.info("加密后的签名 sign = {}", result);

        return result;
    }
}
