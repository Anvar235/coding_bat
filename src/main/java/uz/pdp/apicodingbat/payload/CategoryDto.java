package uz.pdp.apicodingbat.payload;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private String description;
    private Integer languageId;

}
