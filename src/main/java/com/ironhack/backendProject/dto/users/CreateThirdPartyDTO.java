package com.ironhack.backendProject.dto.users;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateThirdPartyDTO {
   @NotEmpty
   private String hashKey;
   @NotEmpty
   private String name;
}
