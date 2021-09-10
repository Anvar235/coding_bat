package uz.pdp.apicodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String condition;

    @Column(nullable = false)
    private String solution;

    private String hint;

    @Column(nullable = false)
    private String method;

    private boolean hasStar;

    @ManyToOne
    private Language language;

    @ManyToOne
    private Category category;



}
