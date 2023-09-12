package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

  private final BeerService beerService;

  public BeerController(BeerService beerService) {
    this.beerService = beerService;
  }

  @GetMapping({"/{beerId}"})
  public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

    return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
  }

  @PostMapping
  ResponseEntity<BeerDto> createBeer(@RequestBody BeerDto beerDto) {
    return new ResponseEntity<>(this.beerService.create(beerDto), HttpStatus.CREATED);
  }

  @PutMapping({"/{beerId}"})
  ResponseEntity updateBeer(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
    BeerDto result = this.beerService.update(beerId, beerDto);
    if (result != null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>("BAD_REQUEST_Invalid_ID", HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping({"/{beerId}"})
  ResponseEntity deleteBeer(@PathVariable UUID beerId) {
    BeerDto deletedBeer = this.beerService.delete(beerId);
    if (deletedBeer != null) {return new ResponseEntity(HttpStatus.NO_CONTENT);}
    return new ResponseEntity<>("BAD_REQUEST_INVALID_ID", HttpStatus.BAD_REQUEST);
  }
}
