package valuyskov.com.HRSystem.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    ErrorResponse(int status,String message){
        this.status = status;
        this.message = message;
    }
}
