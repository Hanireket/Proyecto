package hanireket.com.prototipo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "*Campo obligatorio.(Título)")
    private String title;

    @NotBlank(message = "*Campo obligatorio.(Texto)")
    private String text;

    private LocalDateTime dateTime = LocalDateTime.now();

    private boolean isEdit;

    private LocalDateTime editDateTime;

    private Boolean isOca;
    private Boolean isArts;
    private Boolean isCplx;
    private Boolean isHani;






}
