package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
  BeerDto getBeerById(UUID beerId);

  BeerDto create(BeerDto beerDto);

  BeerDto update(UUID uuid, BeerDto beerDto);

  BeerDto delete(UUID beerId);
}
