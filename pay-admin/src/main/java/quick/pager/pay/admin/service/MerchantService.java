package quick.pager.pay.admin.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import quick.pager.pay.Constants;
import quick.pager.pay.common.constants.ResponseStatus;
import quick.pager.pay.dto.BaseDto;
import quick.pager.pay.dto.admin.MerchantDto;
import quick.pager.pay.mapper.merchant.MerchantMapper;
import quick.pager.pay.model.merchant.Merchant;
import quick.pager.pay.response.Response;
import quick.pager.pay.service.IService;
import quick.pager.pay.vo.MerchantVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MerchantService implements IService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Response doService(BaseDto dto) {
        MerchantDto merchantDto  = (MerchantDto) dto;

        Constants.Operation operation = merchantDto.getOperation();
        Response response = null;
        switch (operation){
            case list:
                response = queryMerchantList(merchantDto);
                break;
            case select:
                response = merchantInfo(merchantDto.getId());
                break;
            case update:
                response = modifyMerchant(merchantDto);
            case delete:
                response = deleteMerchant(merchantDto.getId());
                break;
        }
        return response;
    }


    /**
     * 查询商户列表
     */
    private Response queryMerchantList(MerchantDto dto) {

        PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
        MerchantVO vo = new MerchantVO();
        vo.setBankName(dto.getBankName());
        vo.setBankNum(dto.getBankNum());
        vo.setIdCard(dto.getIdCard());
        vo.setMerchantNo(dto.getMerchantNo());
        vo.setMobile(dto.getMobile());
        vo.setServerStatus(dto.getServerStatus());
        vo.setBeginTime(DateUtil.formatDateTime(DateUtil.date(dto.getBeginTime())));
        vo.setEndTime(DateUtil.formatDateTime(DateUtil.date(dto.getEndTime())));
        List<Merchant> list = merchantMapper.selectMerchant(vo);

        PageInfo<Merchant> pageInfo = new PageInfo<>(list);

        Response<List<Merchant>> resp = new Response<>();
        resp.setTotal(pageInfo.getTotal());
        resp.setData(pageInfo.getList());

        return resp;
    }

    /**
     * 查看商户信息
     */
    private Response merchantInfo(Long id) {

        Merchant merchant = merchantMapper.selectByPrimaryKey(id);

        if (ObjectUtils.isEmpty(merchant)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        Response<Merchant> response = new Response<>();
        response.setData(merchant);
        return response;
    }

    /**
     * 新增或者修改商户
ß     */
    private Response modifyMerchant(MerchantDto dto) {
        // 新增
        if (ObjectUtils.isEmpty(dto.getId())) {
            Merchant merchant = new Merchant();
            merchant.setAddress(dto.getAddress());
            merchant.setBankName(dto.getBankName());
            merchant.setBankNum(dto.getBankNum());
            merchant.setContractName(dto.getContractName());
            merchant.setHighQuality(dto.getHighQuality());
            merchant.setIdCard(dto.getIdCard());
            merchant.setIdCardBehind(dto.getIdCardBehind());
            merchant.setIdCardFront(dto.getIdCardFront());
            merchant.setMerchantName(dto.getMerchantName());
            merchant.setMerchantNo(dto.getMerchantNo());
            merchant.setMobile(dto.getMobile());
            merchant.setPassword(SecureUtil.md5(dto.getPassword()));
            merchant.setLicense(dto.getLicense());
            merchant.setPrivateKey(dto.getPrivateKey());
            merchant.setServerStatus(dto.getServerStatus());
            merchant.setPublicKey(dto.getPublicKey());

            if (!StringUtils.isEmpty(dto.getDeduction())) {
                merchant.setDeduction(new BigDecimal(dto.getDeduction()));
            }
            if (!StringUtils.isEmpty(dto.getRate())) {
                merchant.setRate(new BigDecimal(dto.getRate()));
            }

            merchantMapper.insertSelective(merchant);
        } else {
            Merchant merchant = merchantMapper.selectByPrimaryKey(dto.getId());

            if (ObjectUtils.isEmpty(merchant)) {
                return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
            }
            merchant.setAddress(dto.getAddress());
            merchant.setBankName(dto.getBankName());
            merchant.setBankNum(dto.getBankNum());
            merchant.setContractName(dto.getContractName());
            merchant.setHighQuality(dto.getHighQuality());
            merchant.setIdCard(dto.getIdCard());
            merchant.setIdCardBehind(dto.getIdCardBehind());
            merchant.setIdCardFront(dto.getIdCardFront());
            merchant.setMerchantName(dto.getMerchantName());
            merchant.setMerchantNo(dto.getMerchantNo());
            merchant.setMobile(dto.getMobile());
            merchant.setPassword(dto.getPassword());
            merchant.setLicense(dto.getLicense());
            merchant.setPrivateKey(dto.getPrivateKey());
            merchant.setServerStatus(dto.getServerStatus());
            merchant.setPublicKey(dto.getPublicKey());
            if (!StringUtils.isEmpty(dto.getDeduction())) {
                merchant.setDeduction(new BigDecimal(dto.getDeduction()));
            }
            if (!StringUtils.isEmpty(dto.getRate())) {
                merchant.setRate(new BigDecimal(dto.getRate()));
            }
            merchantMapper.updateByPrimaryKeySelective(merchant);
        }
        return new Response();
    }

    /**
     * 删除商户
ß     */
    private Response deleteMerchant(Long id) {

        Merchant merchant = merchantMapper.selectByPrimaryKey(id);

        if (ObjectUtils.isEmpty(merchant)) {
            return new Response(ResponseStatus.PARAMETER_ERROR.code, ResponseStatus.PARAMETER_ERROR.msg);
        }

        merchant.setDeleteStatus(true);
        merchant.setUpdateTime(new Date());
        merchantMapper.updateByPrimaryKeySelective(merchant);
        return new Response();
    }
}
