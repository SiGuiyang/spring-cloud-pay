package quick.pager.pay.mapper.merchant;

import org.apache.ibatis.annotations.Param;
import quick.pager.pay.mapper.IMapper;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.vo.MerchantVO;

import java.util.List;


public interface MerchantMapper extends IMapper<Long, Merchant> {

    /**
     * 查询商户
     */
    List<Merchant> selectMerchant(MerchantVO vo);

    /**
     * 根据商户号查询商户
     *
     * @param merchantNo 商户号
     */
    Merchant selectMerchantByMerchantNo(@Param("merchantNo") String merchantNo);

}