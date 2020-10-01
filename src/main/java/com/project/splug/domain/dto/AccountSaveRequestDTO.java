package com.project.splug.domain.dto;

import com.project.splug.domain.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountSaveRequestDTO {

    private String date;
    private String usePlace;
    private String useAmount;
    private String other;

    @Builder
    public AccountSaveRequestDTO(String date, String usePlace, String useAmount, String other){
        this.date = date;
        this.usePlace = usePlace;
        this.useAmount = useAmount;
        this.other = other;
    }

    public Account toEntity(){
        return Account.builder()
                .date(date)
                .usePlace(usePlace)
                .useAmount(useAmount)
                .other(other)
                .build();
    }
}
