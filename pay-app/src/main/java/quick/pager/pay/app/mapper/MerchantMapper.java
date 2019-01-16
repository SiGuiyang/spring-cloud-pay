package quick.pager.pay.app.mapper;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.merchant.Merchant;


public interface MerchantMapper extends IMapper<Long, Merchant> {

    /**
     * 根据商户号查询商户
     *
     * @param merchantNo 商户号
     */
    Merchant selectMerchantByMerchantNo(@Param("merchantNo") String merchantNo);

}