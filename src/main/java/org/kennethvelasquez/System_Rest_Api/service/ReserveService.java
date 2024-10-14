package org.kennethvelasquez.System_Rest_Api.service;

import org.kennethvelasquez.System_Rest_Api.model.Reserve;
import org.springframework.http.ResponseEntity;

public interface ReserveService {
    ResponseEntity addReserve(Reserve reserve);
    ResponseEntity getAllReserves();
    ResponseEntity getReserveById(int idReserve);
    ResponseEntity updateReserve(Reserve reserve, int idReserve);
    ResponseEntity deleteReserveById(int idReserve);
}
