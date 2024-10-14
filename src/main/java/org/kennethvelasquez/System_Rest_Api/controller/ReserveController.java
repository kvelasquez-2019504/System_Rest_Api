package org.kennethvelasquez.System_Rest_Api.controller;

import org.kennethvelasquez.System_Rest_Api.model.Reserve;
import org.kennethvelasquez.System_Rest_Api.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/reserve/")
public class ReserveController implements ReserveService {
    private static ArrayList<Reserve> listReserves;
    private static HashMap<Integer, Integer> listUserReserves = new HashMap<>();

    public ReserveController() {
        listReserves = new ArrayList<>();
    }

    @PostMapping("/")
    @Override
    public ResponseEntity addReserve(@RequestBody Reserve reserve) {
        listReserves.add(reserve);
        return ResponseEntity.ok().body(reserve);
    }

    @GetMapping("/")
    @Override
    public ResponseEntity getAllReserves() {
        return ResponseEntity.ok(listReserves.stream().toList());
    }

    @GetMapping("/{idReserve}")
    @Override
    public ResponseEntity getReserveById(@PathVariable int idReserve) {
        Optional <Reserve> reserve = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if (reserve.isPresent()) {
            return ResponseEntity.ok(reserve);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idReserve}")
    @Override
    public ResponseEntity updateReserve(@RequestBody Reserve reserve, @PathVariable int idReserve) {
        Optional <Reserve> reserveSearch = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if(reserveSearch.isPresent()) {
            reserveSearch.get().setReserveDate(reserve.getReserveDate());
            reserveSearch.get().setDescription(reserve.getDescription());
            return ResponseEntity.ok(reserveSearch);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idReserve}")
    @Override
    public ResponseEntity deleteReserveById(@PathVariable int idReserve) {
        Optional <Reserve> reserve = listReserves.stream().filter(
                reserveFind -> reserveFind.getIdReserve() == idReserve).findFirst();
        if(reserve.isPresent()) {
            listReserves.remove(reserve.get());
            return ResponseEntity.ok("Se ha eliminado la reserva: "+reserve.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
