package guru.springframework.msscbrewery.domain;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
  private UUID id;
  private String name;
}
