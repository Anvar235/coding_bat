package uz.pdp.apicodingbat.payload;

import lombok.Data;

@Data
public class AnswerDto {
    private String text;
    private boolean isCorrect;

    private Integer taskId;
    private Integer userId;

}
