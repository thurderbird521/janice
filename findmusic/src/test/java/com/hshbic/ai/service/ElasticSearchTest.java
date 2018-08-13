package com.hshbic.ai.service;

import com.hshbic.ai.FindmusicApp;
import com.hshbic.ai.config.Constants;
import com.hshbic.ai.domain.User;
import com.hshbic.ai.repository.search.UserSearchRepository;
import com.hshbic.ai.repository.UserRepository;
import com.hshbic.ai.service.dto.MusicDTO;
import com.hshbic.ai.service.dto.UserDTO;
import com.hshbic.ai.service.util.RandomUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FindmusicApp.class)
public class ElasticSearchTest {

	@Autowired
    private ElasticsearchTemplate esTemplate; 

    @Before
    public void init() {
       
    }

    @Test
    public void test() {
    	 // SumBuilder sb = AggregationBuilders.sum("tpPrice").field("payPrice");
          BoolQueryBuilder bqb = QueryBuilders.boolQuery();
          bqb.must(QueryBuilders.termQuery("singer.keyword","阿杜"));
         // bqb.must(QueryBuilders.termQuery("song.keyword","简单爱 (Live)"));
      /*    bqb.must(QueryBuilders.boolQuery()
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL_WX_APP))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL_ALI))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL_WX_JS))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL_APP))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_TL_WX_H5))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_WX_GWORLD))
                  .should(QueryBuilders.matchQuery("payPlatform", SuperAppConstant.PAY_PLATFORM_ALI_GWORLD))
                  );*/
    	  SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(bqb).build();
          List<MusicDTO> MusicDTOs = esTemplate.queryForList(searchQuery, MusicDTO.class);
          for (MusicDTO MusicDTO : MusicDTOs) {
              System.out.println(MusicDTO.toString());
          }
    }

 
}
