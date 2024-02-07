package org.example.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileRequestDTO {
    private String encodedData;
    private String fileName;
    private String fileType;
    private String fileUri;
}
