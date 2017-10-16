package nl.payconiq.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import nl.payconiq.request.PostStockVO;
import nl.payconiq.request.PutStockVO;
import nl.payconiq.response.ResultStockVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successCreateStock() throws Exception {

        PostStockVO postStockVO = new PostStockVO();
        postStockVO.setName("Test");
        postStockVO.setCurrentPrice(BigDecimal.TEN);

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/stocks")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(postStockVO))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(postStockVO.getName()))
            .andExpect(jsonPath("$.currentPrice").value(postStockVO.getCurrentPrice()))
            .andReturn();
    }

    @Test
    public void badRequestNegativePriceCreateStock() throws Exception {

        PostStockVO postStockVO = new PostStockVO();
        postStockVO.setName("Test");
        postStockVO.setCurrentPrice(new BigDecimal(-100));

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/stocks")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(postStockVO))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void badRequestBlankNameCreateStock() throws Exception {

        PostStockVO postStockVO = new PostStockVO();
        postStockVO.setName("");
        postStockVO.setCurrentPrice(new BigDecimal(100));

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/stocks")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(postStockVO))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void successDetailStock() throws Exception {

        String stockName = "Detail";
        BigDecimal stockPrice = new BigDecimal(200.5);

        ResultStockVO insertedStockVO = createTestStock(stockName, stockPrice);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/stocks/"+insertedStockVO.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(insertedStockVO.getName()))
            .andExpect(jsonPath("$.currentPrice").value(insertedStockVO.getCurrentPrice()))
            .andExpect(jsonPath("$.id").value(insertedStockVO.getId()))
            .andReturn();
    }

    @Test
    public void notFoundDetailStock() throws Exception {

        String stockName = "Detail";
        BigDecimal stockPrice = new BigDecimal(200.5);

        ResultStockVO insertedStockVO = createTestStock(stockName, stockPrice);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/stocks/"+insertedStockVO.getId())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(insertedStockVO.getName()))
                .andExpect(jsonPath("$.currentPrice").value(insertedStockVO.getCurrentPrice()))
                .andExpect(jsonPath("$.id").value(insertedStockVO.getId()))
                .andReturn();
    }

    @Test
    public void successListStock() throws Exception {

        createTestStock("first", new BigDecimal(100));
        createTestStock("second", new BigDecimal(200));

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/stocks")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), response.getStatus());


        List list = mapper.readValue(response.getContentAsString(),List.class);

        Assert.assertEquals(2,list.size());

    }

    @Test
    public void successUpdateStock() throws Exception {

        ResultStockVO createdStock = createTestStock("update", new BigDecimal(1000.10));

        PutStockVO putStock = new PutStockVO();
        putStock.setId(createdStock.getId());
        putStock.setCurrentPrice(new BigDecimal(1001.30));

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/stocks/"+createdStock.getId())
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(putStock))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(putStock.getId()))
                .andExpect(jsonPath("$.currentPrice").value(putStock.getCurrentPrice()))
                .andReturn();
    }

    @Test
    public void notFoundUpdateStock() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        PutStockVO putStock = new PutStockVO();
        putStock.setId(100);
        putStock.setCurrentPrice(new BigDecimal(1001.30));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/stocks/"+putStock.getId())
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(putStock))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void badRequestNegativePriceUpdateStock() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        ResultStockVO createdStock = createTestStock("update", new BigDecimal(1000.10));

        PutStockVO putStock = new PutStockVO();
        putStock.setId(createdStock.getId());
        putStock.setCurrentPrice(new BigDecimal(-1001.30));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/stocks/"+putStock.getId())
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(putStock))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
    }

    private ResultStockVO createTestStock(String name, BigDecimal price) throws Exception {

        PostStockVO postStockVO = new PostStockVO();
        postStockVO.setName(name);
        postStockVO.setCurrentPrice(price);

        ObjectMapper mapper = new ObjectMapper();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/stocks")
                .accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(postStockVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        return mapper.readValue(response.getContentAsString(),ResultStockVO.class);
    }


}
