package pl.aleksandrabobowska.mikroserwis.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.aleksandrabobowska.mikroserwis.api.dto.NameDto;
import pl.aleksandrabobowska.mikroserwis.api.dto.ResponseDto;
import pl.aleksandrabobowska.mikroserwis.database.entity.NameEntity;
import pl.aleksandrabobowska.mikroserwis.database.repository.NameRepository;
import pl.aleksandrabobowska.mikroserwis.util.Mappings;

import java.util.ArrayList;
import java.util.List;

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
    @GetMapping(Mappings.LIST_ALL_NAMES)
    public ResponseEntity<ResponseDto> listAll() {
        log.info("GET /{}", Mappings.LIST_ALL_NAMES);
        List<NameDto> nameDtoList = new ArrayList<>();
        nameRepository.findAll().forEach(e ->
                nameDtoList.add(NameDto.fromEntity(e)));
        return ResponseEntity.status(200)
                .body(ResponseDto.builder().names(nameDtoList).build());
    }
}