package uz.pdp.apicodingbat.payload;

import lombok.Data;

@Data
public class TaskDto {
    private String name;
    private String condition;
    private String solution;
    private String hint;
    private String method;
    private boolean hasStar;
    private Integer languageID;
    private Integer categoryID;


}
