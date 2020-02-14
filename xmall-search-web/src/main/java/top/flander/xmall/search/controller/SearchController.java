package top.flander.xmall.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import top.flander.xmall.bean.SearchParam;
import top.flander.xmall.bean.SearchSkuInfo;
import top.flander.xmall.service.SearchParamService;

import java.util.List;

@Controller
public class SearchController {

    @Reference
    SearchParamService searchParamService;

    @RequestMapping("list.html")
    public String goList(SearchParam searchParam, ModelMap modelMap){
        List<SearchSkuInfo> searchSkuInfos = searchParamService.getList(searchParam);
        modelMap.put("skuLsInfoList",searchSkuInfos);
        return "list";
    }

    @RequestMapping("goIndex")
    public String goIndex(){
        return "index";
    }
}
