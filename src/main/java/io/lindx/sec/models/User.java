package io.lindx.sec.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private Integer id;
  private String name;
  private String mail;
  private String password;
}
