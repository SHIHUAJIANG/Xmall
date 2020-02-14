package top.flander.xmall.search;


import com.alibaba.dubbo.config.annotation.Reference;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.flander.xmall.bean.PmsSkuInfo;
import top.flander.xmall.bean.SearchSkuInfo;
import top.flander.xmall.service.SkuService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class XmallSearchServiceApplicationTests {

    @Reference
    SkuService skuService;

    @Autowired
    JestClient jestClient;

    @Test
    public void contextLoads() throws IOException {
        List<PmsSkuInfo> pmsSkuInfoList = new ArrayList<>();
        pmsSkuInfoList = skuService.getSkuAll();
        List<SearchSkuInfo> searchSkuInfoList = new ArrayList<>();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList){
            SearchSkuInfo searchSkuInfo = new SearchSkuInfo();
            BeanUtils.copyProperties(pmsSkuInfo,searchSkuInfo);
            searchSkuInfoList.add(searchSkuInfo);
        }

        for (SearchSkuInfo searchSkuInfo : searchSkuInfoList){
        Index put = new Index.Builder(searchSkuInfo).index("xmall").type("pmsSkuInfo").id(searchSkuInfo.getId()).build();
        jestClient.execute(put);
        }

    }
}
