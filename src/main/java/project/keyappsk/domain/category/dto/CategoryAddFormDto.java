package project.keyappsk.domain.category.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAddFormDto {
    @NotBlank
    String name;
}
