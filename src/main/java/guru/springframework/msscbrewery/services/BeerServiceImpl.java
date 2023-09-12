package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by jt on 2019-04-20.
 */
@Service
@Slf4j
public class BeerServiceImpl implements BeerService {
  @Override
  public BeerDto getBeerById(UUID beerId) {
    return BeerDto.builder().id(UUID.randomUUID())
        .beerName("Galaxy Cat")
        .beerStyle("Pale Ale")
        .build();
  }

  @Override
  public BeerDto create(BeerDto beerDto) {
    log.debug("Creating....");
    return BeerDto.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName()).build();
  }
}
