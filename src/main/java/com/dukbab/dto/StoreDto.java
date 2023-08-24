package com.dukbab.dto;

import com.dukbab.domain.isOpen;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Builder
public class StoreDto {
    private isOpen isOpen;
    private int congestion;

}
