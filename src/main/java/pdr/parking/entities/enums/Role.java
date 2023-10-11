package pdr.parking.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Role {

    USER(1),
    ADMIN(2),
    TRAFFIC_GUARD(3);

    private int code;

    public static Role valueOf(int code){
        for(Role value : Role.values()){
            if(value.getCode() == code){
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid Role code");
    }

}
