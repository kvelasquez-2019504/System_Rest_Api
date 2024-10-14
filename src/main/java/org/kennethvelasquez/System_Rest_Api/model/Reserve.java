package org.kennethvelasquez.System_Rest_Api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserve {
    private int idReserve;
    private Date reserveDate;
    private String description;
}
