package guru.springframework.msscbrewery.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {
  @MockBean
  private BeerService beerService;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  private BeerDto beer;

  @Before
  public void setup() {
    this.beer = BeerDto.builder().id(UUID.randomUUID()).beerName("validBeer").
        beerStyle("stylish").upc(123456L).build();
  }

  @Test
  public void createBeer_takeBeerDto_returnCreated() throws Exception {
    BeerDto beerDto = beer;
    beerDto.setId(null);
    String beerDtoToJson = this.objectMapper.writeValueAsString(beerDto);
    BeerDto createdBeer = BeerDto.builder()
        .id(UUID.randomUUID()).beerName("created Beer").upc(1234L).beerStyle("stylish").build();
    given(beerService.create(beerDto)).willReturn(createdBeer);
    mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
        .andExpect(status().isCreated());
  }

  @Test
  public void createBeer_returnBadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void updateBeer_takeIdAndBeerDto_returnOk() throws Exception {
    BeerDto beerDto = beer;
    beerDto.setId(UUID.randomUUID());
    String beerDtoToJson = this.objectMapper.writeValueAsString(beerDto);
    beer.setBeerName("updated beer");
    given(beerService.update(any(), any())).willReturn(beer);
    mockMvc.perform(put("/api/v1/beer/" + beer.getId()).contentType(MediaType.APPLICATION_JSON).content(beerDtoToJson))
        .andExpect(status().isNoContent());
  }

  @Test
  public void updateBeer_takeId_returnBadRequest() throws Exception {
    mockMvc.perform(put("/api/v1/beer/" + beer.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

}
