package quick.pager.pay.app.mapper;


import org.apache.ibatis.annotations.Mapper;
import quick.pager.pay.common.mapper.IMapper;
import quick.pager.pay.model.merchant.MerchantBalance;

@Mapper
public interface MerchantBalanceMapper extends IMapper<Long,MerchantBalance> {

}
