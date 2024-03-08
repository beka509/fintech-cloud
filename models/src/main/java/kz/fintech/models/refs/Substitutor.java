package kz.fintech.models.refs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Substitutor {
    private int curatorId;
    private int substitutorId;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
