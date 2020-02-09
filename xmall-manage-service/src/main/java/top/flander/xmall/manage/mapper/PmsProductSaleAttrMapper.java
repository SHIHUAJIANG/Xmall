package top.flander.xmall.manage.mapper;




import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import top.flander.xmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface PmsProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {
    List<PmsProductSaleAttr> selectSpuSaleAttrListCheckBySku(@Param("productId") String productId, @Param("skuId") String skuId);
}
