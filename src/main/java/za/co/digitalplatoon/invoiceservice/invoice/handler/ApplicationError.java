package za.co.digitalplatoon.invoiceservice.invoice.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ApplicationError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<NestedError> nestedErrors = new ArrayList<>();

    private ApplicationError() {
        timestamp = LocalDateTime.now();
    }

    ApplicationError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApplicationError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }
}
