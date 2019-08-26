package pl.aleksandrabobowska.mikroserwis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseDto {

    private List<NameDto> names;
}

