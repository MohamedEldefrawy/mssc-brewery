package guru.springframework.msscbrewery.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Beer {
  private UUID id;
  private String beerName;
  private String beerStyle;
  private Long upc;
}
