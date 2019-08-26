package pl.aleksandrabobowska.mikroserwis.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.aleksandrabobowska.mikroserwis.api.dto.NameDto;
import pl.aleksandrabobowska.mikroserwis.database.entity.NameEntity;
import pl.aleksandrabobowska.mikroserwis.database.repository.NameRepository;
import pl.aleksandrabobowska.mikroserwis.util.Mappings;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
@RestController
public class ApiController {

    private NameRepository nameRepository;

    @Autowired
    public void setNameRepository(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    @PostMapping(Mappings.ADD_NEW_NAME)
    public ResponseEntity<NameDto> addNewName(@RequestBody NameDto nameDto) {
        log.info("POST /{}, content: {}", Mappings.ADD_NEW_NAME, nameDto.toString());
        if (isEmpty(nameDto.getName())) {
            return ResponseEntity.badRequest().build();
        }
        NameEntity nameEntity = new NameEntity();
        nameEntity.setName(nameDto.getName());

        nameRepository.save(nameEntity);
        return ResponseEntity.ok()
                .body(NameDto.fromEntity(nameEntity));
    }
}