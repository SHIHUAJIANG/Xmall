package top.flander.xmall.service;



import top.flander.xmall.bean.SearchParam;
import top.flander.xmall.bean.SearchSkuInfo;

import java.util.List;

public interface SearchService {
    List<SearchSkuInfo> list(SearchParam searchParam);
}
