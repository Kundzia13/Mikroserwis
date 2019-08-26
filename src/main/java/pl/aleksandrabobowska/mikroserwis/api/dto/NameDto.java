package pl.aleksandrabobowska.mikroserwis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.aleksandrabobowska.mikroserwis.database.entity.NameEntity;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NameDto {

    private Long id;
    private String name;

    public static NameDto fromEntity(NameEntity entity) {
        return new NameDto(entity.getId(), entity.getName());
    }
}
