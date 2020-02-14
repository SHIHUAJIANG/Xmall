package top.flander.xmall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import top.flander.xmall.bean.PmsSkuAttrValue;
import top.flander.xmall.bean.SearchParam;
import top.flander.xmall.bean.SearchSkuInfo;
import top.flander.xmall.service.SearchParamService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchParamServiceImpl implements SearchParamService {

    @Autowired
    JestClient jestClient;

    @Override
    public List<SearchSkuInfo> getList(SearchParam searchParam) {
        String dslStr = getSearchDsl(searchParam);
        System.err.println(dslStr);
        // 用api执行复杂查询
        List<SearchSkuInfo> pmsSearchSkuInfos = new ArrayList<>();
        Search search = new Search.Builder(dslStr).addIndex("xmall").addType("pmsSkuInfo").build();
        SearchResult execute = null;
        try {
            execute = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<SearchResult.Hit<SearchSkuInfo, Void>> hits = execute.getHits(SearchSkuInfo.class);
        for (SearchResult.Hit<SearchSkuInfo, Void> hit : hits) {
            SearchSkuInfo source = hit.source;
            System.out.println(hit.highlight);
            Map<String, List<String>> highlight = hit.highlight;
            System.out.println(highlight);
            String skuName = highlight.get("skuName").get(0);
            source.setSkuName(skuName);
            pmsSearchSkuInfos.add(source);
        }

        System.out.println(pmsSearchSkuInfos.size());
        return pmsSearchSkuInfos;
    }

    private String getSearchDsl(SearchParam pmsSearchParam) {

        List<PmsSkuAttrValue> skuAttrValueList = pmsSearchParam.getSkuAttrValueList();
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();

        // jest的dsl工具
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        // filter
        if(StringUtils.isNotBlank(catalog3Id)){
            TermQueryBuilder termQueryBuilder = new TermQueryBuilder("catalog3Id",catalog3Id);
            boolQueryBuilder.filter(termQueryBuilder);
        }
        if(skuAttrValueList!=null){
            for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
                TermQueryBuilder termQueryBuilder = new TermQueryBuilder("skuAttrValueList.valueId",pmsSkuAttrValue.getValueId());
                boolQueryBuilder.filter(termQueryBuilder);
            }
        }

        // must
        if(StringUtils.isNotBlank(keyword)){
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName",keyword);
            boolQueryBuilder.must(matchQueryBuilder);
        }

        // query
        searchSourceBuilder.query(boolQueryBuilder);

        // highlight
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red;'>");
        highlightBuilder.field("skuName");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlight(highlightBuilder);
        // sort
        searchSourceBuilder.sort("id", SortOrder.DESC);
        // from
        searchSourceBuilder.from(0);
        // size
        searchSourceBuilder.size(20);

        return searchSourceBuilder.toString();

    }
}
