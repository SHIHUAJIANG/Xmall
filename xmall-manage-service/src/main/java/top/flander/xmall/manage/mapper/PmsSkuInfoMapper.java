package top.flander.xmall.manage.mapper;


import tk.mybatis.mapper.common.Mapper;
import top.flander.xmall.bean.PmsSkuInfo;

import java.util.List;

public interface PmsSkuInfoMapper extends Mapper<PmsSkuInfo>{
    List<PmsSkuInfo> selectSkuSaleAttrValueListBySpu(String productId);
}
