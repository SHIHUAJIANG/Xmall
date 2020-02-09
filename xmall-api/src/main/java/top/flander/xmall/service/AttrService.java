package top.flander.xmall.service;


import top.flander.xmall.bean.PmsBaseAttrInfo;
import top.flander.xmall.bean.PmsBaseAttrValue;
import top.flander.xmall.bean.PmsBaseSaleAttr;

import java.util.List;


public interface AttrService {

    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
