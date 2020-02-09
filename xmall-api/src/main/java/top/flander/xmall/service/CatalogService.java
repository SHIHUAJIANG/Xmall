package top.flander.xmall.service;


import top.flander.xmall.bean.PmsBaseCatalog1;
import top.flander.xmall.bean.PmsBaseCatalog2;
import top.flander.xmall.bean.PmsBaseCatalog3;

import java.util.List;

public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
