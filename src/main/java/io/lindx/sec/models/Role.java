package io.lindx.sec.models;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role implements GrantedAuthority {
  private Integer id;
  private String role;

	@Override
	public String getAuthority() {
		return role;
	}
}
